import input.BinaryTreeData;
import input.model.BinaryTree;

public class CountUnivalueTrees extends BaseAlgorithm<BinaryTreeData> {

    CountUnivalueTrees(BinaryTreeData binaryTreeData) {
        super(binaryTreeData);
    }

    @Override
    void execute() {
        BinaryTree.Node root = input.data.root;
    }

    @Override
    protected String describe() {
        return "Counting univalue trees for " + input.asText();
    }
}
