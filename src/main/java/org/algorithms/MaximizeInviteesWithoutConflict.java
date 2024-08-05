package org.algorithms;

import org.algorithms.input.IntWithListData;

import java.util.List;

public class MaximizeInviteesWithoutConflict extends BaseAlgorithm<IntWithListData<int[]>> {

    private static int maxInvitees = 0;

    public MaximizeInviteesWithoutConflict(IntWithListData<int[]> intWithListData) {
        super(intWithListData);
    }

    @Override
    public void execute() {
        maxInvitees = 0;
        int people = input.first;
        List<int[]> conflicts = input.second;
        boolean[][] conflictGraph = new boolean[people][people];
        for (int[] conflict : conflicts) {
            conflictGraph[conflict[0]][conflict[1]] = true;
            conflictGraph[conflict[1]][conflict[0]] = true;
        }

        boolean[] selected = new boolean[people];
        findMaxInvitees(conflictGraph, selected, 0, 0);
        System.out.println("Max invitees: " + maxInvitees);
    }

    /**
     * Brute force backtracking algorithm with O(2^n * n) time complecity.
     */
    private void findMaxInvitees(boolean[][] conflictGraph, boolean[] selected, int person, int invited) {
        if (person == conflictGraph.length) {
            maxInvitees = Math.max(maxInvitees, invited);
            return;
        }

        boolean canInviteAPerson = true;
        for (int fr = 0; fr < conflictGraph.length; fr++) {
            if (conflictGraph[person][fr] && selected[fr]) {
                canInviteAPerson = false;
                break;
            }
        }

        if (canInviteAPerson) {
            selected[person] = true;
            findMaxInvitees(conflictGraph, selected, person + 1, invited + 1);
            selected[person] = false;
        }
        // try without me
        findMaxInvitees(conflictGraph, selected, person + 1, invited);
    }

    @Override
    protected String describe() {
        return "Maximizing Invitees Without Conflicts for people";
    }
}
