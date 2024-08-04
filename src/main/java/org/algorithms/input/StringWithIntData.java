package input;

import input.base.AlgorithmPairData;

public class StringWithIntData extends AlgorithmPairData<String, Integer> {


    public StringWithIntData(String first, Integer second) {
        super(first, second);
    }

    @Override
    public String asText() {
        return "first: " + first + ", second: " + second;
    }
}
