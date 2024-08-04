package org.algorithms.input;

import org.algorithms.AlgorithmData;

import java.util.Arrays;

public class ArrayData extends AlgorithmData<int[]> {

    public ArrayData(int[] data) {
        super(data);
    }

    @Override
    public String asText() {
        return Arrays.toString(data);
    }
}