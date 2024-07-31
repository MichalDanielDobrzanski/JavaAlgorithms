import input.IntWithListData;

import java.util.List;

public class MaximizeInviteesWithoutConflict extends BaseAlgorithm<IntWithListData<int[]>> {

    private static int maxInvitees = 0;

    MaximizeInviteesWithoutConflict(IntWithListData<int[]> intWithListData) {
        super(intWithListData);
    }

    @Override
    void execute() {
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

    private void findMaxInvitees(boolean[][] conflictGraph, boolean[] selected, int i, int count) {
        if (i == conflictGraph.length) {
            maxInvitees = Math.max(maxInvitees, count);
            return;
        }

        boolean canInvite = true;
        for (int j = 0; j < conflictGraph.length; j++) {
            if (conflictGraph[i][j] && selected[j]) {
                canInvite = false;
                break;
            }
        }

        if (canInvite) {
            selected[i] = true;
            findMaxInvitees(conflictGraph, selected, i + 1, count + 1);
            selected[i] = false;
        }
        // try without me
        findMaxInvitees(conflictGraph, selected, i + 1, count);
    }

    @Override
    protected String describe() {
        return "Maximizing Invitees Without Conflicts for people";
    }
}
