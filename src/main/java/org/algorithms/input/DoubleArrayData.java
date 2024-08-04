package org.algorithms.input;

import org.algorithms.AlgorithmData;
import org.algorithms.BaseAlgorithmData;

import java.util.Arrays;

public class DoubleArrayData extends AlgorithmData<int[][]> implements BaseAlgorithmData {
    public DoubleArrayData(int[][] data) {
        super(data);
    }

    @Override
    public String asText() {
        return Arrays.deepToString(data);
    }
}