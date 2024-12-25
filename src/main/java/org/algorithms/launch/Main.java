package org.algorithms.launch;

import org.algorithms.*;
import org.algorithms.input.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<BaseAlgorithm> algos = new ArrayList<>(List.of(new MedianOfSortedArrays(new DoubleArrayData(new int[][]{new int[]{1, 2}, new int[]{3, 4}})), new LongestPalindrome(new StringData("babac")), new GasStations(new StringData(""))));
        algos.addAll(IntStream.range(7, 9).boxed().map((number) -> new BinarySearch(new ArrayWithIntData(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, number + 1))).toList());
        algos.addAll(List.of(new MaxTimeForTickets(new StringData("")), new ThreeSum(new StringData("")), new MarkdownToHtmlConverter(new StringData(""))));

        algos.addAll(Stream.of(new ArrayWithIntData(new int[]{2, 4, 5}, 7), new ArrayWithIntData(new int[]{1, 4, 5}, 11)).map(CoinChange::new).toList());

        algos.addAll(Stream.of(new ArrayWithIntData(new int[]{4, 2, 2, 7, 8, 1, 2, 8, 1, 0}, 8)).map(SmallestSubarrayGreaterOrEqualThan::new).toList());


        algos.addAll(Stream.of(new StringWithIntData("AAAHHIBC", 2)).map(LongestSubstringLengthWithKMostDistinctCharacters::new).toList());

        algos.addAll(Stream.of(new ArrayData(new int[]{7, 6, 4, 1, 9, 8, 3})).map(QuickSort::new).toList());

        algos.addAll(Stream.of(new CharArrayWithStringData(new char[][]{new char[]{'A', 'B', 'C', 'E'}, new char[]{'S', 'F', 'C', 'S'}, new char[]{'A', 'D', 'E', 'E'}}, "ABCCED")).map(WordSearch::new).toList());

        algos.add(new FriendsOfFriends(new AdjacencyListData(new List[]{List.of(2, 3),  // John(0)
                List.of(2), // Paul(1)
                List.of(0, 1),  // Tom(2)
                List.of(0, 4),  // George(3)
                List.of(3, 5),  // Rob(4)
                List.of(4), // Cory(5)
        })));

        maximizeInviteesWithoutConflict(algos);

        countUnivalueTrees(algos);

        flipMonochromaticImage(algos);

        algos.add(new MemoizationDecorator());

        for (BaseAlgorithm algo : algos) {
            System.out.println(algo.describeWithInput() + ":");
            algo.execute();
            System.out.println("\n");
        }
    }

    private static void flipMonochromaticImage(List<BaseAlgorithm> algos) {
        algos.add(new FlipMonochromeBytes());
    }

    private static void countUnivalueTrees(List<BaseAlgorithm> algos) {
        // 1
        BinaryTree.Node leftLeft = new BinaryTree.Node(1, null, null);
        BinaryTree.Node leftRight = new BinaryTree.Node(1, null, null);
        BinaryTree.Node left = new BinaryTree.Node(1, leftLeft, leftRight);
        BinaryTree.Node rightLeft = new BinaryTree.Node(1, null, null);
        BinaryTree.Node rightRight = new BinaryTree.Node(1, null, null);
        BinaryTree.Node right = new BinaryTree.Node(1, rightLeft, rightRight);
        BinaryTree.Node root = new BinaryTree.Node(1, left, right);
        BinaryTreeData binaryTreeData = new BinaryTreeData(new BinaryTree(root));
        algos.add(new CountUnivalueTrees(binaryTreeData));

        // 2
        prepareAnotherBinaryTreeInput(algos);
    }

    private static void prepareAnotherBinaryTreeInput(List<BaseAlgorithm> algos) {
        BinaryTree.Node root = new BinaryTree.Node(1);
        root.left = new BinaryTree.Node(2);
        root.right = new BinaryTree.Node(3);
        root.left.left = new BinaryTree.Node(4);
        root.left.right = new BinaryTree.Node(5);
        root.right.left = new BinaryTree.Node(6);
        root.right.right = new BinaryTree.Node(7);

        BinaryTreeData binaryTreeData = new BinaryTreeData(new BinaryTree(root));
        algos.add(new CountUnivalueTrees(binaryTreeData));
    }

    private static void maximizeInviteesWithoutConflict(List<BaseAlgorithm> algorithms) {
        // Maximum number of invitees: 2
        var maximizeInviteesWithoutConflictInput1 = List.of(new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{0, 4});
        algorithms.add(new MaximizeInviteesWithoutConflict(new IntWithListData<>(5, maximizeInviteesWithoutConflictInput1)));

        var maximizeInviteesWithoutConflictInput2 = List.of(new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 3}, new int[]{1, 4}, new int[]{2, 5}, new int[]{3, 6}, new int[]{4, 5}, new int[]{6, 7}, new int[]{7, 8}, new int[]{8, 9}, new int[]{1, 9});
        algorithms.add(new MaximizeInviteesWithoutConflict(new IntWithListData<>(10, maximizeInviteesWithoutConflictInput2)));
    }
}