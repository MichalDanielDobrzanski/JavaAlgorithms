package input;

import input.base.AlgorithmData;

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