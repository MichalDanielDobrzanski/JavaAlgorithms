package org.algorithms.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DijkstrasAlgorithmTest {

    private final DijkstrasAlgorithm algo = new DijkstrasAlgorithm();

    @Test
    public void testBasicGraph() {
        int n = 5;
        int start = 0;
        int[][] weights = {
                {0, 1, 10},
                {0, 2, 3},
                {1, 2, 1},
                {2, 1, 4},
                {2, 3, 2},
                {1, 3, 2},
                {3, 4, 7},
                {4, 0, 9}
        };

        int[] expected = {0, 7, 3, 5, 12};
        int[] actual = algo.dijkstra(n, start, weights);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDisconnectedGraph() {
        int n = 4;
        int start = 0;
        int[][] weights = {
                {0, 1, 5}
        };

        int[] actual = algo.dijkstra(n, start, weights);
        int[] expected = {0, 5, Integer.MAX_VALUE, Integer.MAX_VALUE};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSingleNode() {
        int n = 1;
        int[][] weights = {};
        int[] actual = algo.dijkstra(n, 0, weights);
        int[] expected = {0};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testNoEdges() {
        int n = 3;
        int[][] weights = {};
        int[] actual = algo.dijkstra(n, 1, weights);
        int[] expected = {Integer.MAX_VALUE, 0, Integer.MAX_VALUE};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testTwoWayPath() {
        int n = 3;
        int[][] weights = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 2, 1}
        };
        int[] actual = algo.dijkstra(n, 0, weights);
        int[] expected = {0, 1, 2};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testCycle() {
        int n = 4;
        int[][] weights = {
                {0, 1, 1},
                {1, 2, 1},
                {2, 3, 1},
                {3, 0, 1}
        };
        int[] actual = algo.dijkstra(n, 0, weights);
        int[] expected = {0, 1, 2, 3};

        assertArrayEquals(expected, actual);
    }
}
