package org.algorithms;

import org.algorithms.input.BinaryTree;
import org.algorithms.input.BinaryTreeData;

/**
 * 1
 * / \
 * 1   1
 * / \ / \
 * 1  1 1  1
 * (7)
 * <p>
 * 1
 * / \
 * 2   3
 * / \ / \
 * 4  5 6  7
 * (4)
 */
public class CountUnivalueTrees extends BaseAlgorithm<BinaryTreeData> {

    public CountUnivalueTrees(BinaryTreeData binaryTreeData) {
        super(binaryTreeData);
    }

    @Override
    public void execute() {
        BinaryTree.Node root = input.data.root;
        int count = countSubtrees(root).count;
        System.out.println("Subtrees count: " + count);
    }

    private Tuple countSubtrees(BinaryTree.Node node) {
        if (node == null) return new Tuple(true, 0);

        Tuple leftNode = countSubtrees(node.left);
        Tuple rightNode = countSubtrees(node.right);

        boolean isUnival = leftNode.isUnivalue && rightNode.isUnivalue;
        int count = leftNode.count + rightNode.count;

        if (node.left != null && node.left.value != node.value) {
            isUnival = false;
        }

        if (node.right != null && node.right.value != node.value) {
            isUnival = false;
        }

        if (isUnival) {
            count += 1;
        }

        return new Tuple(isUnival, count);
    }

    private record Tuple(
            boolean isUnivalue,
            int count
    ) {
    }

    @Override
    protected String describe() {
        return "Counting univalue trees";
    }
}
