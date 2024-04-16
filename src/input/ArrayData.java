package input;

import java.util.Arrays;

public class ArrayData implements Textable {
    public final int[] array;

    public ArrayData(int[] array) {
        this.array = array;
    }

    @Override
    public String asText() {
        return Arrays.toString(array);
    }
}