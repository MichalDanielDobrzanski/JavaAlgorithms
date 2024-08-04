package org.algorithms.input;

import org.algorithms.AlgorithmData;

public class IntData extends AlgorithmData<Integer> {

    public IntData(Integer data) {
        super(data);
    }

    @Override
    public String asText() {
        return "Input: " + data;
    }
}
