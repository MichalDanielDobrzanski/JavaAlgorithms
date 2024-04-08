package input;

public class StringData implements Textable {

    public final String data;

    public StringData(String data) {
        this.data = data;
    }

    @Override
    public String asText() {
        return data;
    }
}
