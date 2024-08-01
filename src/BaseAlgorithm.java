import input.base.BaseAlgorithmData;

public abstract class BaseAlgorithm<INPUT extends BaseAlgorithmData> {
    protected INPUT input;

    BaseAlgorithm(INPUT input) {
        this.input = input;
    }

    abstract void execute();

    protected abstract String describe();

    public String describeWithInput() {
        return describe() + " for " + input.asText();
    }

}
