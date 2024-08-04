package org.algorithms.input;

import org.algorithms.AlgorithmPairData;

import java.util.Arrays;

public class CharArrayWithStringData extends AlgorithmPairData<char[][], String> {

    public CharArrayWithStringData(char[][] first, String second) {
        super(first, second);
    }

    @Override
    public String asText() {
        return "First: " + Arrays.deepToString(first) + " second: " + second;
    }
}
