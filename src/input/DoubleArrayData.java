package input;

import java.util.Arrays;

public class DoubleArrayData implements Textable {

    public final int[][] data;

    public DoubleArrayData(int[][] data) {
        this.data = data;
    }

    @Override
    public String asText() {
        return Arrays.deepToString(data);
    }
}