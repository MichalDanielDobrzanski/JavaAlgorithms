package org.algorithms.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Writer-preference tweak (prevents writer starvation)
 * Track waiting writers and block new readers when a writer is queued:
 * <p>
 * Requirement: Multiple readers can access the shared resource simultaneously, but writers must have exclusive access
 * If you want it crisp as invariants:
 * Safety (mutual exclusion):
 * - writerActive ⇒ readers == 0
 * - readers > 0 ⇒ !writerActive
 * Liveness (progress): if no one holds the resource, some waiter eventually proceeds.
 */
class ReadersWritersLockWithWriterPreference {
    private final ReentrantLock lock = new ReentrantLock(true); // fair optional
    private final Condition okToRead = lock.newCondition();
    private final Condition okToWrite = lock.newCondition();

    private int readers = 0;
    private int waitingWriters = 0;
    private boolean writerActive = false;

    public void readLock() throws InterruptedException {
        lock.lock();
        try {
            while (writerActive || waitingWriters > 0) okToRead.await(); // block if a writer is waiting
            readers++;
        } finally {
            lock.unlock();
        }
    }

    public void readUnlock() {
        lock.lock();
        try {
            if (--readers == 0) okToWrite.signal(); // now writer can enter
        } finally {
            lock.unlock();
        }
    }

    public void writeLock() throws InterruptedException {
        lock.lock();
        try {
            waitingWriters++;
            while (writerActive || readers > 0) okToWrite.await(); // exclusivity
            writerActive = true;
        } finally {
            waitingWriters--;
            lock.unlock();
        }
    }

    public void writeUnlock() {
        lock.lock();
        try {
            writerActive = false;
            okToWrite.signal();   // maybe a writer waiting
            okToRead.signalAll(); // and all waiting readers may proceed
        } finally {
            lock.unlock();
        }
    }
}