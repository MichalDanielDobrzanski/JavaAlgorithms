package input;

import input.base.AlgorithmData;

import java.util.Arrays;

public class DoubleArrayData extends AlgorithmData<int[][]> {
    public DoubleArrayData(int[][] data) {
        super(data);
    }

    @Override
    public String asText() {
        return Arrays.deepToString(data);
    }
}