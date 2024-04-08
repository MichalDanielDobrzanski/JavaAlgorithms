package input;

import java.util.Arrays;

public class ArrayWithIntData implements Textable {
    public final int[] array;
    public final int number;

    public ArrayWithIntData(int[] array, int number) {
        this.array = array;
        this.number = number;
    }

    @Override
    public String asText() {
        return Arrays.toString(array) + " and " + number;
    }
}