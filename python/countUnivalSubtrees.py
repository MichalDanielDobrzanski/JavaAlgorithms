from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def countUnivalTree(root: Optional[TreeNode]) -> int:
    def count(node: Optional[TreeNode]) -> (bool, int):
        if node is None:
            return True, 0

        # leaf case
        if node.left is None and node.right is None:
            return True, 1

        lunival, lc = count(node.left)
        runival, rc = count(node.right)

        total = lc + rc

        isUnival = lunival and runival

        if node.left and node.left.val != node.val:
            isUnival = False

        if node.right and node.right.val != node.val:
            isUnival = False

        if isUnival:
            total += 1

        return isUnival, total

    return count(root)[1]


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    # Test Case 1
    root = None
    print(countUnivalTree(root))  # Expected: 0

    # Test Case 2
    root = TreeNode(1)
    print(countUnivalTree(root))  # Expected: 1

    # Test Case 3
    root = TreeNode(1,
                    TreeNode(1, TreeNode(1), TreeNode(1)),
                    TreeNode(1, None, TreeNode(1)))
    print(countUnivalTree(root))  # Expected: 6

    # Test Case 4
    root = TreeNode(5,
                    TreeNode(1, TreeNode(5), TreeNode(5)),
                    TreeNode(5, None, TreeNode(5)))
    print(countUnivalTree(root))  # Expected: 4

    # Test Case 5
    root = TreeNode(1,
                    TreeNode(2, TreeNode(4), TreeNode(5)),
                    TreeNode(3))
    print(countUnivalTree(root))  # Expected: 3
