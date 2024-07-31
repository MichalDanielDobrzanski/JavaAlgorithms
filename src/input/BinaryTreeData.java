package input;

import input.base.AlgorithmData;
import input.model.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeData extends AlgorithmData<BinaryTree> {

    public BinaryTreeData(BinaryTree data) {
        super(data);
    }

    @Override
    public String asText() {
        return "Binary Tree: " + print();
    }

    private String print() {
        List<List<Integer>> content = new LinkedList<>();

        Queue<NodeWithLevel> queue = new LinkedList<>();
        queue.add(new NodeWithLevel(data.root, 0));

        while (!queue.isEmpty()) {
            NodeWithLevel curr = queue.poll();

            if (content.get(curr.level) == null) {
                content.add(List.of(curr.node.value));
            } else {
                content.get(curr.level).add(curr.node.value);
            }

            if (curr.node.left != null) {
                var withLevel = new NodeWithLevel(curr.node.left, curr.level + 1);
                queue.add(withLevel);
            }
            if (curr.node.right != null) {
                var withLevel = new NodeWithLevel(curr.node.right, curr.level + 1);
                queue.add(withLevel);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (List<Integer> list : content) {
            for (int val : list) {
                sb.append(val);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private record NodeWithLevel(
            BinaryTree.Node node,
            int level
    ) {
    }
}
