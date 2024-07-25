package input;

import java.util.List;

public record IntWithListData<T>(int value, List<T> inputs) implements Textable {

    @Override
    public String asText() {
        return "Input: " + value;
    }
}
