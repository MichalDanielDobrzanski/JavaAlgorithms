package org.algorithms;

public abstract class AlgorithmPairData<T, R> implements BaseAlgorithmData {
    public T first;
    public R second;

    public AlgorithmPairData(T first, R second) {
        this.first = first;
        this.second = second;
    }
}
