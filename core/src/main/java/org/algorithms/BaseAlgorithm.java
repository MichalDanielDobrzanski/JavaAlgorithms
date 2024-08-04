package input.base;

public abstract class BaseAlgorithm<INPUT extends BaseAlgorithmData> {
    protected INPUT input;

    public BaseAlgorithm(INPUT input) {
        this.input = input;
    }

    public abstract void execute();

    protected abstract String describe();

    public String describeWithInput() {
        return describe() + " for " + input.asText();
    }

}
