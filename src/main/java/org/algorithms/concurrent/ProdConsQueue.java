package org.algorithms.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProdConsQueue<T> {
    private final Queue<T> q = new LinkedList<>();
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    /**
     * Invariant:
     * 0 ≤ queue.size() ≤ capacity
     */
    public ProdConsQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.capacity = capacity;
    }

    /**
     * // You choose the one that fits your system’s policy.
     * // 1. wait (block) - as here
     * // 2. drop the task
     * // 3. grow the queue (dynamic capacity)
     * Blocking put: waits while full.
     */
    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while (q.size() == capacity) {     // while, not if (spurious wakeups)
                // That’s the key — blocking is one valid policy, but not mandatory.
                notFull.await();
            }
            q.add(item);
            notEmpty.signal();                  // wake one waiting consumer
        } finally {
            lock.unlock();
        }
    }

    /**
     * Option 2: Drop-on-full (non-blocking)
     * Non-blocking try; returns false if full.
     */
    public boolean offer(T item) {
        lock.lock();
        try {
            if (q.size() == capacity) return false;
            q.add(item);
            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Blocking take: waits while empty.
     */
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (q.isEmpty()) {
                notEmpty.await();
            }
            T item = q.poll();
            notFull.signal();                   // wake one waiting producer
            return item;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Non-blocking try; returns null if empty.
     */
    public T poll() {
        lock.lock();
        try {
            if (q.isEmpty()) return null;
            T item = q.poll();
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return q.size();
        } finally {
            lock.unlock();
        }
    }
}
