package org.algorithms.graph;

public class MaxInviteesWithoutProblems {

    /**
     * Problem restated in my own words:
     * You can have n = at most 50 people.
     * Some pairs of people dislike each other.
     * You want to invite the maximum number of people such that no two invited people dislike each other.
     * <p>
     * MIS is a classic NP-hard problem in general graphs.
     * For arbitrary graphs with n=50, the worst-case solution is exponential (2^50).
     * <p>
     * But n=50 is small enough that you might get away with:
     * Branch & bound / backtracking with pruning.
     * <p>
     * f(R)=max(1+f(R∖(N(v)∪{v})),f(R∖{v}))
     */
    public int solve(int n, int[][] dislikes) {
        // public API: dislikes pairs are 0-based
        // response: max
        maxInvited = 0;

        // step 1:
        boolean[][] adjMatrix = new boolean[n][n];
        for (int[] dislike: dislikes) {
            int from = dislike[0];
            int to = dislike[1];
            adjMatrix[from][to] = true;
            adjMatrix[to][from] = true;
        }

        // step 2: - what recursive decision tree naturally represents that?
        getMax(n, adjMatrix, 0, new boolean[n]);

        return maxInvited;
    }

    // recurrence formula - going forward:
    // prev state => next state
    // getMax(i,chosen) <= max(getMax(i+1, chosen), getMax(i+1, chosen with i))
    private void getMax(int n, boolean[][] adjMatrix, int node, boolean[] invited) {
        int currSize = 0;
        for (var inv: invited) if (inv) currSize++;

        // base case
        if (node == n) {
            maxInvited = Math.max(maxInvited, currSize);
            return;
        }

        // optimisation for pruning branches:
        int possibleToInvite = n - node;
        if (currSize + possibleToInvite <= maxInvited) return;

        // check for current node
        boolean canInvite = !invited[node];
        for (int inv = 0; inv < n; inv++) {
            if (invited[inv] && adjMatrix[node][inv]) {
                canInvite = false;
                break;
            }
        }

        getMax(n, adjMatrix, node+1, invited); // no invited case
        if (canInvite) {
            // invited case - with backtracking
            invited[node] = true;
            getMax(n, adjMatrix, node+1, invited);
            invited[node] = false;
        }
    }

    private int maxInvited = 0;
}
