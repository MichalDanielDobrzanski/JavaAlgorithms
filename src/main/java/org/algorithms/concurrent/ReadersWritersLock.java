package org.algorithms.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Readers-writers.
 * Requirement: Multiple readers can access the shared resource simultaneously, but writers must have exclusive access
 * If you want it crisp as invariants:
 * Safety (mutual exclusion):
 * - writerActive ⇒ readers == 0
 * - readers > 0 ⇒ !writerActive
 * Liveness (progress): if no one holds the resource, some waiter eventually proceeds.
 */
class ReadersWritersLock {
    private final Lock lock = new ReentrantLock();
    private final Condition okToRead = lock.newCondition();
    private final Condition okToWrite = lock.newCondition();

    private int readers = 0;
    private boolean writerActive = false;

    // Optional: add writer-preference (avoid writer starvation)
    private int waitingWriters = 0; // <-- preference knob

    public void readLock() throws InterruptedException {
        lock.lock();
        try {
            // If you want reader-preference instead (maximize read throughput),
            // drop waitingWriters > 0 from the reader wait condition—just keep while (writerActive).
            while (writerActive || waitingWriters > 0) { // block if a writer is waiting
                okToRead.await();
            }
            readers++;
        } finally {
            lock.unlock();
        }
    }

    public void readUnlock() {
        lock.lock();
        try {
            if (--readers == 0) okToWrite.signal();
        } finally {
            lock.unlock();
        }
    }

    public void writeLock() throws InterruptedException {
        lock.lock();
        try {
            waitingWriters++;
            try {
                while (writerActive || readers > 0) okToWrite.await();
            } finally {
                waitingWriters--;
            }
            writerActive = true;
        } finally {
            lock.unlock();
        }
    }

    public void writeUnlock() {
        lock.lock();
        try {
            writerActive = false;
            okToWrite.signal();   // let next writer go first (preference)
            okToRead.signalAll(); // then readers
        } finally {
            lock.unlock();
        }
    }
}
