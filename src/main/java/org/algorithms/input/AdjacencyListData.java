package org.algorithms.input;

import org.algorithms.AlgorithmData;

import java.util.Arrays;
import java.util.List;

public class AdjacencyListData extends AlgorithmData<List<Integer>[]> {

    public AdjacencyListData(List<Integer>[] data) {
        super(data);
    }

    @Override
    public String asText() {
        return Arrays.toString(data);
    }
}