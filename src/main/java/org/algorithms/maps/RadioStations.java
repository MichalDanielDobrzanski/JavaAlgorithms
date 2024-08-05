package org.algorithms.maps;

import org.algorithms.AlgorithmCustomData;
import org.algorithms.BaseAlgorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 2D grid representing a map where each cell can either be land or water (m by n).
 * <p>
 * You need to find the shortest path from a start point to an end point,
 * but you can only travel through land cells.
 * Start point is (0,0).
 * End point is (m-1,n-1).
 * <p>
 * Additionally, you need to place radio stations on the grid such that each station
 * can communicate with other stations within a certain distance.
 * <p>
 * - You can move in 4 directions: up, down, left, and right.
 * - You can only move through land cells.
 * - The grid is represented by a 2D array where 1 indicates land and 0 indicates water.
 * - The distance between two stations must not exceed a given limit.
 */
public class RadioStations extends BaseAlgorithm<AlgorithmCustomData> {

    public RadioStations() {
        super(new AlgorithmCustomData());
    }

    @Override
    public void execute() {
        int[][] grid = {
                {1, 1, 1, 1, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 0, 1, 1, 1}
        };
        int xDest = grid.length - 1, yDest = grid[0].length - 1;
        final int pathDistance = solve(grid, xDest, yDest);
        System.out.println("Is there a path? Shortest path distance: " + pathDistance);
    }

    private record Point(
            int x,
            int y,
            int distance
    ) {

    }

    private final int[] xOffsets = new int[]{0, -1, 0, 1};
    private final int[] yOffsets = new int[]{-1, 0, 1, 0};

    private boolean isValid(int[][] grid, int x, int y) {
        return x >= 0 && x <= grid.length - 1 && y >= 0 && y <= grid[0].length - 1;
    }

    private int solve(int[][] grid, int xDest, int yDest) {
        // 1. find a shortest path
        // 2. place radio stations along the route

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Point> queue = new LinkedList<>();
        Point start = new Point(0, 0, 0);
        visited[0][0] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            final Point point = queue.poll();

            if (point.x == xDest && point.y == yDest) {
                return point.distance;
            }

            for (int i = 0; i < 4; i++) {
                int newX = point.x + xOffsets[i];
                int newY = point.y + yOffsets[i];

                if (isValid(grid, newX, newY) && !visited[newX][newY]) {
                    // BFS
                    visited[newX][newY] = true;
                    queue.add(new Point(newX, newY, point.distance + 1));
                }
            }
        }
        return -1;
    }

    @Override
    protected String describe() {
        return "Building radio stations in at most k distance to reach out to the end";
    }
}
