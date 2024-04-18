package input;

import java.util.Arrays;

public class CharArrayWithStringData implements Textable {

    public final char[][] matrix;
    public final String input;

    public CharArrayWithStringData(char[][] matrix, String input) {
        this.matrix = matrix;
        this.input = input;
    }

    @Override
    public String asText() {
        return "Matrix: " + Arrays.deepToString(matrix) + " input: " + input;
    }
}
