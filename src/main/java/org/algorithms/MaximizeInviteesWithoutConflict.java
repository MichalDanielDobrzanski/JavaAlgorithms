package org.algorithms;

import org.algorithms.input.IntWithListData;

import java.util.Arrays;
import java.util.Comparator;
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
        for (int[] c : conflicts) {
            conflictGraph[c[0]][c[1]] = true;
            conflictGraph[c[1]][c[0]] = true;
        }

        boolean[] selected = new boolean[people];
        bruteForce(conflictGraph, selected, 0, 0);
        System.out.println("Max invitees brute force: " + maxInvitees);

        findGreedy(conflictGraph, people);
    }

    /**
     * Brute force backtracking algorithm with O(2^n * n) time complexity.
     */
    private void bruteForce(boolean[][] conflictGraph, boolean[] selected, int person, int invitees) {
        if (person == conflictGraph.length) {
            maxInvitees = Math.max(maxInvitees, invitees);
            return;
        }

        boolean canInvite = true;
        for (int fr = 0; fr < conflictGraph.length; fr++) {
            if (conflictGraph[person][fr] && selected[fr]) {
                canInvite = false;
                break;
            }
        }

        if (canInvite) {
            selected[person] = true;
            bruteForce(conflictGraph, selected, person + 1, invitees + 1);
            selected[person] = false;
        }
        bruteForce(conflictGraph, selected, person + 1, invitees);
    }


    /**
     * Finding in a greedy way. We can start from the least conflicts.
     * O(n^2) time complexity due to trying every person with every conflict.
     */
    public void findGreedy(boolean[][] conflictGraph, int people) {
        int[] degrees = new int[people];
        for (int i = 0; i < people; i++) {
            for (int j = 0; j < people; j++) {
                if (conflictGraph[i][j]) {
                    degrees[i]++;
                }
            }
        }
        Integer[] sortedPeople = new Integer[people];
        for (int i = 0; i < people; i++) {
            sortedPeople[i] = i;
        }
        Arrays.sort(sortedPeople, Comparator.comparing(p -> degrees[p]));

        System.out.println("Greedy O(n^2) approach:");
        System.out.println(Arrays.toString(sortedPeople));

        boolean[] selected = new boolean[people];
        int maxInvitees = 0;
        for (int i : sortedPeople) {
            boolean canInvite = true;
            for (int j = 0; j < people; j++) {
                if (conflictGraph[i][j] && selected[j]) {
                    canInvite = false;
                    break;
                }
            }
            if (canInvite) {
                selected[i] = true;
                maxInvitees++;
            }
        }

        System.out.println("Max invitees greedy: " + maxInvitees);
    }

    @Override
    protected String describe() {
        return "Maximizing Invitees Without Conflicts for people";
    }
}
