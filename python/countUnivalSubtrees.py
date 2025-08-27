from typing import Optional, Tuple


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def countUnivalTree(root: Optional[TreeNode]) -> int:
    def dfs(node: Optional[TreeNode]) -> Tuple[bool, int]:
        if not node:
            # Empty subtree is unival, contributes 0 to count
            return True, 0

        left_unival, left_count = dfs(node.left)
        right_unival, right_count = dfs(node.right)

        count = left_count + right_count

        if (left_unival
                and right_unival
                and (node.left is None or node.left.val == node.val)
                and (node.right is None or node.right.val == node.val)):
            return True, count + 1

        return False, count

    return dfs(root)[1]


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
