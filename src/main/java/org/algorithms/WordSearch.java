package org.algorithms;

import org.algorithms.input.CharArrayWithStringData;

public class WordSearch extends BaseAlgorithm<CharArrayWithStringData> {
    public WordSearch(CharArrayWithStringData charArrayWithStringData) {
        super(charArrayWithStringData);
    }

    @Override
    public void execute() {
        char[][] board = input.first;
        String word = input.second;

        boolean exists = exist(board, word);
        System.out.println("Exists " + word + "? : " + exists);
    }

    // Time complexity is O(m * n * 4^w), where w is word length
    public boolean exist(char[][] board, String word) {
        // finding:
        //      A
        //     / \
        //    AB  AS
        //   / \
        //  BA ABC

        int rows = board.length;
        int columns = board[0].length;
        boolean[][] seen = new boolean[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                // word = ABCCED
                // I can track the current expected letter
                boolean exists = traverse(board, r, c, word, 0, seen);
                if (exists) return true;
            }
        }
        return false;
    }


    private boolean traverse(char[][] board, int r, int c, String word, int index, boolean[][] seen) {
        // we are at the end of a correct solution. This must be BEFORE boundary checks as this one is a quick win!
        if (index == word.length()) return true;

        // all weird index cases
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return false;

        // we cannot go back
        if (seen[r][c]) return false;

        // we don't want wrong chars
        if (board[r][c] != word.charAt(index)) return false;

        // proceed with a valid char (part of searched word), check unseen, have a valid index

        seen[r][c] = true;

        boolean right = traverse(board, r, c + 1, word, index + 1, seen);
        boolean bottom = traverse(board, r + 1, c, word, index + 1, seen);
        boolean left = traverse(board, r, c - 1, word, index + 1, seen);
        boolean top = traverse(board, r - 1, c, word, index + 1, seen);

        if (right || bottom || left || top) return true;

        seen[r][c] = false;
        return false;

    }

    @Override
    protected String describe() {
        return "Word search problem";
    }
}
