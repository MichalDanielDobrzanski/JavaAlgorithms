import input.BinaryTreeData;
import input.base.BaseAlgorithm;
import input.model.BinaryTree;

/**
 *     1
 *    / \
 *   1   1
 *  / \ / \
 * 1  1 1  1
 * (7)
 *
 *     1
 *    / \
 *   2   3
 *  / \ / \
 * 4  5 6  7
 * (4)
 */
public class CountUnivalueTrees extends BaseAlgorithm<BinaryTreeData> {

    CountUnivalueTrees(BinaryTreeData binaryTreeData) {
        super(binaryTreeData);
    }

    @Override
    public void execute() {
        BinaryTree.Node root = input.data.root;
        int count = countSubtrees(root).count;
        System.out.println("Subtrees count: " + count);
    }

    private NodeResult countSubtrees(BinaryTree.Node node) {
        if (node == null) return new NodeResult(true, 0);
        NodeResult left = countSubtrees(node.left);
        NodeResult right = countSubtrees(node.right);

        boolean isUnivalue = true;

        if (node.left != null && node.left.value != node.value) {
            isUnivalue = false;
        }
        if (node.right != null && node.right.value != node.value) {
            isUnivalue = false;
        }

        if (isUnivalue) {
            return new NodeResult(true, left.count + right.count + 1);
        } else {
            return new NodeResult(false, left.count + right.count);
        }
    }

    private record NodeResult(
            boolean isUnivalue,
            int count
    ) {
    }

    @Override
    protected String describe() {
        return "Counting univalue trees";
    }
}
