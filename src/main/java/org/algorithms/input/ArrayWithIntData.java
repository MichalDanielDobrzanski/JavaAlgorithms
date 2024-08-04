package org.algorithms.input;

import org.algorithms.AlgorithmPairData;

import java.util.Arrays;

public class ArrayWithIntData extends AlgorithmPairData<int[], Integer> {

    public ArrayWithIntData(int[] first, Integer second) {
        super(first, second);
    }

    @Override
    public String asText() {
        return "First: " + Arrays.toString(first) + ", second: " + second;
    }
}