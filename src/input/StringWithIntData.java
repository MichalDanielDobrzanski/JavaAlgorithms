package input;

public class StringWithIntData implements Textable {

    public final String input;
    public final int number;

    public StringWithIntData(String input, int number) {
        this.input = input;
        this.number = number;
    }

    @Override
    public String asText() {
        return "Input: " + input + ", Num: " + number;
    }
}
