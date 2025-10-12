package org.algorithms.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FileSyncPool implements AutoCloseable {
    private final BlockingQueue<String> queue;
    private final ConcurrentHashMap<String, AtomicInteger> inflight = new ConcurrentHashMap<>();
    private final ExecutorService exec;
    private volatile boolean stopping = false;

    public FileSyncPool(int workers, int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
        this.exec = Executors.newFixedThreadPool(workers);
        for (int i = 0; i < workers; i++) exec.submit(this::workerLoop);
    }

    /**
     * Submit a file; coalesces duplicates. Non-blocking: drops if queue full? Your call.
     */
    public void submit(String fileId) {
        inflight.compute(fileId, (k, counter) -> {
            if (counter == null) {
                // reserve and enqueue once
                if (!queue.offer(k)) {
                    // backpressure policy: either throw, or fall back to put(), or unreserve
                    throw new RejectedExecutionException("queue full");
                }
                return new AtomicInteger(0);
            } else {
                counter.incrementAndGet(); // mark a pending rerun
                return counter;
            }
        });
    }

    private void workerLoop() {
        try {
            while (!stopping || !queue.isEmpty()) {
                String file = queue.poll(100, TimeUnit.MILLISECONDS);
                if (file == null) continue;

                AtomicInteger counter = inflight.get(file);
                if (counter == null) {
                    // Rare: race where submit removed? Skip.
                    continue;
                }

                try {
                    process(file); // <-- your actual work (may throw)
                } catch (Throwable t) {
                    // Decide policy: re-enqueue on failure or count as handled
                    // Here we re-enqueue once to retry:
                    counter.incrementAndGet();
                } finally {
                    // Atomically snapshot pending reruns and reset to 0
                    while (true) {
                        int pending = counter.getAndSet(0);
                        if (pending > 0) {
                            // coalesced rerun: exactly one re-enqueue
                            queue.offer(file);
                            break; // keep reservation; run again
                        }
                        // No pending reruns; attempt to release reservation
                        if (inflight.remove(file, counter)) {
                            break; // released; new submits will enqueue anew
                        }
                        // Someone incremented after we reset to 0 but before removal.
                        // Loop to observe the new pending.
                    }
                }
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Replace with real implementation; should be idempotent or safe to retry.
     */
    protected void process(String fileId) {
        // do sync work for fileId
    }

    /**
     * Graceful shutdown: stop taking new, drain queue, finish ongoing.
     */
    @Override
    public void close() {
        stopping = true;
        exec.shutdown();
        try {
            exec.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            exec.shutdownNow();
        }
    }
}
