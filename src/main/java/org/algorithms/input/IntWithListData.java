package org.algorithms.input;

import org.algorithms.AlgorithmPairData;

import java.util.List;

public class IntWithListData<R> extends AlgorithmPairData<Integer, List<R>> {

    public IntWithListData(Integer first, List<R> second) {
        super(first, second);
    }

    @Override
    public String asText() {
        return "Input: " + first;
    }
}
