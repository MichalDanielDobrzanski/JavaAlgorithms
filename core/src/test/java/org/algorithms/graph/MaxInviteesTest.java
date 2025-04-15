package org.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxInviteesNoConflictTest {

    MaxInviteesNoConflict solver = new MaxInviteesNoConflict();

    @Test
    void testNoConflicts_AllInvited() {
        int n = 5;
        List<int[]> conflicts = List.of(); // No conflicts
        List<Integer> invitees = solver.maxInvitees(n, conflicts);

        assertEquals(5, invitees.size(), "All people should be invited");
    }

    @Test
    void testAllConflictWithEachOther_OnlyOneInvited() {
        int n = 4;
        List<int[]> conflicts = List.of(
                new int[]{0, 1},
                new int[]{0, 2},
                new int[]{0, 3},
                new int[]{1, 2},
                new int[]{1, 3},
                new int[]{2, 3}
        );
        List<Integer> invitees = solver.maxInvitees(n, conflicts);

        assertEquals(1, invitees.size(), "Only one person can be invited");
    }

    @Test
    void testLinearConflicts_EveryOtherInvited() {
        int n = 6;
        List<int[]> conflicts = List.of(
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{2, 3},
                new int[]{3, 4},
                new int[]{4, 5}
        );
        List<Integer> invitees = solver.maxInvitees(n, conflicts);

        assertEquals(3, invitees.size(), "Should be able to invite every other person");
    }

    @Test
    void testEvenCycle() {
        int n = 4;
        List<int[]> conflicts = List.of(
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{2, 3},
                new int[]{3, 0}
        );
        List<Integer> invitees = solver.maxInvitees(n, conflicts);

        assertEquals(2, invitees.size(), "Max independent set in cycle of even length is n/2");
    }

    @Test
    void testOddCycle() {
        int n = 5;
        List<int[]> conflicts = List.of(
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{2, 3},
                new int[]{3, 4},
                new int[]{4, 0}
        );
        List<Integer> invitees = solver.maxInvitees(n, conflicts);

        assertEquals(2, invitees.size(), "Max independent set in odd cycle is floor(n/2)");
    }

    @Test
    void testDisconnectedComponents() {
        int n = 6;
        List<int[]> conflicts = List.of(
                new int[]{0, 1},
                new int[]{2, 3},
                new int[]{4, 5}
        );
        List<Integer> invitees = solver.maxInvitees(n, conflicts);

        assertEquals(3, invitees.size(), "Should pick one from each pair");
    }

    @Test
    void testEmptyGraph() {
        int n = 0;
        List<int[]> conflicts = List.of();
        List<Integer> invitees = solver.maxInvitees(n, conflicts);

        assertEquals(0, invitees.size(), "Empty graph should return empty list");
    }
}
