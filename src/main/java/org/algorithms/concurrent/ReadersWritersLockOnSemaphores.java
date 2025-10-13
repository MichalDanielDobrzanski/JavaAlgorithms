package org.algorithms.concurrent;

import java.util.concurrent.Semaphore;

public class ReadersWritersLockOnSemaphores {
    private final Semaphore roomEmpty = new Semaphore(1);     // 1 means room free
    private final Semaphore readCountMutex = new Semaphore(1); // protects readers
    private int readers = 0;

    // Readers share
    public void readLock() throws InterruptedException {
        readCountMutex.acquire();
        try {
            if (readers == 0) {
                roomEmpty.acquire(); // first reader blocks writers
            }
            readers++;
        } finally {
            readCountMutex.release();
        }
    }

    public void readUnlock() throws InterruptedException {
        readCountMutex.acquire();
        try {
            readers--;
            if (readers == 0) {
                roomEmpty.release(); // last reader lets writers in
            }
        } finally {
            readCountMutex.release();
        }
    }

    // Writers exclusive
    public void writeLock() throws InterruptedException {
        roomEmpty.acquire(); // wait until room has no readers/writers
    }

    public void writeUnlock() {
        roomEmpty.release();
    }
}
