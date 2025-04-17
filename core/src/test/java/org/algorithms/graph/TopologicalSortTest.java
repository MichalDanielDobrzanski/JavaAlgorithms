package org.algorithms.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortTest {

    private final TopologicalSort sorter = new TopologicalSort();

    // In a topological sort:
    // For every directed edge u → v, u must come before v in the ordering.
    private boolean isTopologicalOrder(int[] result, int[][] edges) {
        // Map node to position
        int[] pos = new int[result.length];
        for (int i = 0; i < result.length; i++) {
            pos[result[i]] = i;
        }

        for (int[] edge : edges) {
            if (pos[edge[0]] >= pos[edge[1]]) {
                return false;
            }
        }

        return true;
    }

    @Test
    void testSimpleDAG() {
        int[][] edges = {
                {5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}
        };
        int[] result = sorter.topSort(6, edges);
        assertEquals(6, result.length);
        assertTrue(isTopologicalOrder(result, edges));
    }

    @Test
    void testCycleGraph() {
        int[][] edges = {
                {0, 1}, {1, 2}, {2, 0}
        };
        int[] result = sorter.topSort(3, edges);
        assertEquals(0, result.length);
    }

    @Test
    void testEmptyGraph() {
        int[][] edges = {};
        int[] result = sorter.topSort(0, edges);
        assertArrayEquals(new int[0], result);
    }

    @Test
    void testSingleNodeNoEdges() {
        int[][] edges = {};
        int[] result = sorter.topSort(1, edges);
        assertArrayEquals(new int[]{0}, result);
    }

    @Test
    void testDisconnectedGraph() {
        int[][] edges = {
                {0, 1}, {2, 3}
        };
        int[] result = sorter.topSort(4, edges);
        assertEquals(4, result.length);
        assertTrue(isTopologicalOrder(result, edges));
    }
}
