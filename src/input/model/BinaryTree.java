package input.model;

public class BinaryTree {

    public Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
