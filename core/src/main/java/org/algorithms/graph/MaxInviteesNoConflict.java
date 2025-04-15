package org.algorithms.graph;

import java.util.*;

public class MaxInviteesNoConflict {

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
        backtrack(0, n, adjList, new HashSet<>(), new ArrayList<>());
        return maxInvitees;
    }

    /**
     * DFS over problem space.
     *
     * blocked: set of people who are not allowed to be invited (they conflict with someone already invited).
     *  - like visited in DFS
     * currentSet: current invite list being built.
     *  - like currentPath in DFS
     */
    private void backtrack(int curr,
                           int n,
                           List<Set<Integer>> graph,
                           Set<Integer> blocked,
                           List<Integer> currentSet) {
        if (curr == n) {
            if (currentSet.size() > maxSize) {
                maxSize = currentSet.size();
                maxInvitees = new ArrayList<>(currentSet);
            }
            return;
        }

        // Option 1: Skip this person
        backtrack(curr + 1, n, graph, blocked, currentSet);

        // Option 2: Try Inviting the Current Person
        if (!blocked.contains(curr)) {
            // Add them to the invite list.
            currentSet.add(curr);

            // Remember who we just blocked so we can undo it later.
            Set<Integer> newlyBlocked = new HashSet<>();

            // Look at all the people they fight with.
            for (int enemy : graph.get(curr)) {
                if (blocked.add(enemy)) {
                    newlyBlocked.add(enemy);
                }
            }

            backtrack(curr + 1, n, graph, blocked, currentSet);

            // Backtrack: undo changes
            currentSet.remove(currentSet.size() - 1);
            blocked.removeAll(newlyBlocked);
        }
    }
}
