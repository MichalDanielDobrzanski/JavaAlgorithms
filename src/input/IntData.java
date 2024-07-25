package input;

public record IntData(int value) implements Textable {

    @Override
    public String asText() {
        return "Input: " + value;
    }
}
