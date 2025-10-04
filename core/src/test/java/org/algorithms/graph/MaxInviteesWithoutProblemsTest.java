package org.algorithms.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxInviteesWithoutProblemsTest {

    @Test
    public void testEmptyGraph_noDislikes() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 5;
        int[][] dislikes = new int[0][0];
        assertEquals(5, solver.solve(n, dislikes), "No dislikes -> invite everyone");
    }

    @Test
    public void testSingleNode() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 1;
        int[][] dislikes = new int[0][0];
        assertEquals(1, solver.solve(n, dislikes), "Single node -> 1 invitee");
    }

    @Test
    public void testZeroNodes() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 0;
        int[][] dislikes = new int[0][0];
        assertEquals(0, solver.solve(n, dislikes), "Zero nodes -> zero invitees");
    }

    @Test
    public void testCompleteGraph_allPairwiseDislike() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 4;
        // complete graph: every pair dislikes each other
        int[][] dislikes = {
                {0, 1}, {0, 2}, {0, 3},
                {1, 2}, {1, 3},
                {2, 3}
        };
        assertEquals(1, solver.solve(n, dislikes), "Complete graph -> max independent set is 1");
    }

    @Test
    public void testTriangle_cycle3() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 3;
        int[][] dislikes = {{0, 1}, {1, 2}, {2, 0}}; // triangle
        assertEquals(1, solver.solve(n, dislikes), "Triangle -> MIS = 1");
    }

    @Test
    public void testPathOfThree_nodes0_1_2() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 3;
        int[][] dislikes = {{0, 1}, {1, 2}}; // path of length 2
        assertEquals(2, solver.solve(n, dislikes), "Path of 3 nodes -> MIS = 2 (choose endpoints)");
    }

    @Test
    public void testTwoDisjointEdges() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 4;
        int[][] dislikes = {{0, 1}, {2, 3}}; // two disjoint edges
        assertEquals(2, solver.solve(n, dislikes), "Two disjoint edges -> choose one from each edge, MIS = 2");
    }

    @Test
    public void testCycle4_square() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 4;
        int[][] dislikes = {{0, 1}, {1, 2}, {2, 3}, {3, 0}}; // cycle of 4
        assertEquals(2, solver.solve(n, dislikes), "4-cycle -> MIS = 2");
    }

    @Test
    public void testDuplicateAndReversedEdgesHandled() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 3;
        // duplicate and reversed entries; should still treat as undirected single edge
        int[][] dislikes = {{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 1}};
        assertEquals(2, solver.solve(n, dislikes), "Triangle disguised with duplicates/reversed pairs -> MIS = 2");
    }

    @Test
    public void testDisconnectedNodesAndEdges() {
        MaxInviteesWithoutProblems solver = new MaxInviteesWithoutProblems();
        int n = 6;
        // component 1: path 0-1-2 (MIS = 2)
        // component 2: isolated nodes 3,4,5 (each can be invited)
        int[][] dislikes = {{0, 1}, {1, 2}};
        // total MIS = 2 (from path) + 3 (isolated) = 5
        assertEquals(5, solver.solve(n, dislikes), "Disconnected components -> sum of MIS per component");
    }
}
