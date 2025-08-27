from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head

        prev = None
        curr = head

        # Loop invariant: 'prev' is the head of the reversed prefix,
        # and 'curr' is the head of the not-yet-reversed suffix.
        while curr:
            nextnode = curr.next
            curr.next = prev

            prev = curr
            curr = nextnode

        return prev


def build_list(values):
    """Helper: list[int] -> ListNode|None"""
    head = None
    tail = None
    for v in values:
        node = ListNode(v)
        if head is None:
            head = tail = node
        else:
            tail.next = node
            tail = node
    return head


def to_py_list(head: Optional[ListNode]):
    """Helper: ListNode|None -> list[int]"""
    out = []
    while head:
        out.append(head.val)
        head = head.next
    return out


def print_list(head: Optional[ListNode]):
    """Helper: pretty print a linked list"""
    vals = to_py_list(head)
    print(" -> ".join(map(str, vals)) if vals else "∅")


if __name__ == "__main__":
    s = Solution()

    # Test 1: Empty list
    head = build_list([])
    res = s.reverseList(head)
    assert to_py_list(res) == [], "Empty list should remain empty"

    # Test 2: Single element
    head = build_list([42])
    res = s.reverseList(head)
    assert to_py_list(res) == [42], "Single element should remain the same"

    # Test 3: Two elements
    head = build_list([1, 2])
    res = s.reverseList(head)
    assert to_py_list(res) == [2, 1], "Two elements should reverse"

    # Test 4: Multiple elements
    head = build_list([1, 2, 3, 4, 5])
    res = s.reverseList(head)
    assert to_py_list(res) == [5, 4, 3, 2, 1], "List should reverse fully"

    # Test 5: With duplicates
    head = build_list([1, 1, 2, 3, 3])
    res = s.reverseList(head)
    assert to_py_list(res) == [3, 3, 2, 1, 1], "Duplicates should reverse normally"

    # Optional: randomized sanity test
    import random

    for _ in range(20):
        arr = [random.randint(-5, 5) for _ in range(random.randint(0, 10))]
        head = build_list(arr)
        res = s.reverseList(head)
        assert to_py_list(res) == list(reversed(arr)), f"Random case failed: {arr}"

    # If you want to see sample outputs:
    demo = build_list([10, 20, 30])
    print("Original:")
    print_list(demo)
    demo_rev = s.reverseList(demo)
    print("Reversed:")
    print_list(demo_rev)

    print("All tests passed ✅")
