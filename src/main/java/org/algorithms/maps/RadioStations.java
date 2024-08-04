package org.algorithms.maps;

import org.algorithms.AlgorithmCustomData;
import org.algorithms.BaseAlgorithm;

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
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 0, 1},
                {1, 0, 1, 1, 1}
        };
        int maxDistanceBetweenStations = 2;
        solve(grid, maxDistanceBetweenStations);
    }

    private void solve(int[][] grid, int maxDistanceBetweenStations) {
        // TODO: solve
    }

    @Override
    protected String describe() {
        return "Building radio stations in at most k distance to reach out to the end";
    }
}
