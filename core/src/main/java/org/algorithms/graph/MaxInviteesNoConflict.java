package org.algorithms.graph;

import java.util.*;

public class MaxInviteesNoConflict {

    private static final boolean useBacktracking = false;

    private int maxSize = 0;
    List<Integer> maxInvitees;

    public List<Integer> maxInvitees(int n, List<int[]> conflicts) {
        maxSize = 0;
        maxInvitees = new LinkedList<>();

        List<Set<Integer>> adjList = new ArrayList<>(n);
        for (int node = 0; node < n; node++) adjList.add(new HashSet<>());

        for (int[] c : conflicts) {
            adjList.get(c[0]).add(c[1]);
            adjList.get(c[1]).add(c[0]);
        }

        // find largest set of nodes, where no two are connected — that's called a maximum independent set.

        // "Finding the maximum independent set is NP-Hard in general, but since n = 50, it's small enough to use backtracking with pruning."
        if (useBacktracking) {
            backtrack(0, n, adjList, new HashSet<>(), new ArrayList<>());
        } else {
            bruteForce(0, n, adjList, new ArrayList<>());
        }
        return maxInvitees;
    }

    /**
     * Time complexity: O(2^n)
     * Problem: Maximise independent set - currentSet.
     * <p>
     * "If I include this node, I block its neighbors"
     * <p>
     * DFS over problem space.
     * <p>
     * blocked: set of people who are not allowed to be invited (they conflict with someone already invited).
     * - like visited in DFS
     * currentSet: current invite list being built.
     * - like currentPath in DFS
     */
    private void backtrack(int curr,
                           int n,
                           List<Set<Integer>> graph,
                           Set<Integer> blocked,
                           List<Integer> currentSet) {
        // If even adding all remaining people would not beat the current best size,
        // I can stop early — that’s a heuristic bound and avoids wasting time.
        if (currentSet.size() + (n - curr) <= maxSize) return;

        // leaf case
        if (curr == n) {
            if (currentSet.size() > maxSize) {
                maxSize = currentSet.size();
                maxInvitees = new ArrayList<>(currentSet);
            }
            return;
        }

        // Option 1: Skip this person
        backtrack(curr + 1, n, graph, blocked, currentSet);

        // Option 2: Include the person, block other people (enemies)
        //  If a person is in the blocked set, I know they conflict with someone I already invited
        //  so I skip the entire subtree where that person is included.
        //  That immediately cuts off a big part of the decision tree.
        if (!blocked.contains(curr)) {
            currentSet.add(curr);

            // Remember who we just blocked so we can undo it later
            Set<Integer> newBlocks = new HashSet<>();

            for (int enemy : graph.get(curr)) {
                // Add them to the blocked list (they can’t be invited later).
                if (blocked.add(enemy)) {
                    newBlocks.add(enemy);
                }
            }

            backtrack(curr + 1, n, graph, blocked, currentSet);

            // backtrack - restore
            currentSet.remove(currentSet.size() - 1);
            blocked.removeAll(newBlocks);
        }
    }

    /**
     * Time complexity: O(2^n * n)
     */
    private void bruteForce(int curr, int n,
                            List<Set<Integer>> graph,
                            List<Integer> currentSet) {
        if (curr == n) {
            if (currentSet.size() > maxSize) {
                maxSize = currentSet.size();
                maxInvitees = new ArrayList<>(currentSet);
            }
            return;
        }

        // Option 1: Don't invite current person
        bruteForce(curr + 1, n, graph, currentSet);

        // Option 2: Invite them, if they don’t conflict with anyone invited so far
        boolean canInvite = true;
        for (int invited : currentSet) {
            if (graph.get(curr).contains(invited)) {
                canInvite = false;
                break;
            }
        }

        if (canInvite) {
            currentSet.add(curr);
            // take with me
            bruteForce(curr + 1, n, graph, currentSet);

            currentSet.remove(currentSet.size() - 1);
        }
    }
}
