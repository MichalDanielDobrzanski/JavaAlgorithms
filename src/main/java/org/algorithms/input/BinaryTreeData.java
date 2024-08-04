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

            if (content.size() <= curr.level) {
                var list = new LinkedList<Integer>();
                list.add(curr.node.value);
                content.add(list);
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

        StringBuilder sb = new StringBuilder("\n");
        int spacing = content.size();
        for (List<Integer> list : content) {
            for (int val : list) {
                StringBuilder spacingBuilder = new StringBuilder("");
                spacingBuilder.append(" ".repeat(Math.max(0, spacing)));
                sb.append(spacingBuilder).append(val);
            }
            spacing--;
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
