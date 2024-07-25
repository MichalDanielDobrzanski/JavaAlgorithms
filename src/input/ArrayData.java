package input;

import java.util.Arrays;

public record ArrayData(int[] array) implements Textable {

    @Override
    public String asText() {
        return Arrays.toString(array);
    }
}