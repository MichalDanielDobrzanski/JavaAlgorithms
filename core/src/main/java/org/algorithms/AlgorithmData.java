package org.algorithms;

public abstract class AlgorithmData<T>implements BaseAlgorithmData {
    public T data;

    public AlgorithmData(T data) {
        this.data = data;
    }
    public abstract String asText();
}
