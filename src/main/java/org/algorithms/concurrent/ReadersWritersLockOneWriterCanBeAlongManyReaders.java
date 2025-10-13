package org.algorithms.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Single-Writer / Multi-Reader with overlap
 * Allow one writer to run while readers read safely.
 * That is only correct if reads are from an immutable snapshot or via optimistic read + validation. Two idiomatic ways:
 */
public class ReadersWritersLockOneWriterCanBeAlongManyReaders<T> {

    private final AtomicReference<T> ref;

    public ReadersWritersLockOneWriterCanBeAlongManyReaders(T initial) {
        this.ref = new AtomicReference<>(initial);
    }

    // readers get a lock-free, consistent snapshot
    public T read() {
        return ref.get();
    }

    public void write(java.util.function.UnaryOperator<T> mutate) {
        while (true) {
            T cur = ref.get();
            T next = mutate.apply(cur);   // build new immutable snapshot
            if (ref.compareAndSet(cur, next)) break;
        }
    }
}
