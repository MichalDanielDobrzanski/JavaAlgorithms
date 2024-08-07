package org.algorithms.input;

import org.algorithms.AlgorithmData;

public class StringData extends AlgorithmData<String> {

    public StringData(String data) {
        super(data);
    }

    @Override
    public String asText() {
        return data;
    }
}
