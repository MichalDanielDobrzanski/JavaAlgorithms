import input.Textable;

public abstract class BaseAlgorithm<INPUT extends Textable> {
    protected INPUT input;

    BaseAlgorithm(INPUT input) {
        this.input = input;
    }

    abstract void execute();

    protected abstract String describe();

    String describeWithInput() {
        return describe() + " for " + input.asText();
    }

}
