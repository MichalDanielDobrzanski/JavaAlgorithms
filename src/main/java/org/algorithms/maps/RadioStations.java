package org.algorithms.maps;

import org.algorithms.AlgorithmCustomData;
import org.algorithms.BaseAlgorithm;

import java.util.*;

/**
 * You are given a 2D grid representing a map where each cell can either be land or water (m by n).
 * <p>
 * You need to find the shortest path from a start point to an end point,
 * but you can only travel through land cells.
 * Start point is (0,0).
 * End point is (m-1,n-1).
 * <p>
 * Place the least number of radio stations along the shortest path with a minimum distance of
 * k between the stations.
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
        final List<Point> path = solve(grid, xDest, yDest);
        System.out.println("Is there a path? Shortest path distance: " + (path.size() - 1));
        System.out.println("Points along the shortest path:");
        for (Point point : path) {
            System.out.println("\t- (" + point.x + ", " + point.y + ")");
        }

        int minDistance = 3;
        final List<Point> radioStationPositions = placeStations(path, minDistance);
        System.out.println("Placed radio stations at:");
        for (Point point : radioStationPositions) {
            System.out.println("\t- (" + point.x + ", " + point.y + ")");
        }
    }

    private record Point(
            int x,
            int y,
            int distance
    ) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private final int[] xOffsets = new int[]{0, -1, 0, 1};
    private final int[] yOffsets = new int[]{-1, 0, 1, 0};

    private boolean isValid(int[][] grid, int x, int y) {
        return x >= 0 && x <= grid.length - 1 && y >= 0 && y <= grid[0].length - 1 && grid[x][y] == 1;
    }

    private List<Point> solve(int[][] grid, int xDest, int yDest) {
        // 1. find the shortest path
        // 2. place radio stations along the route

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Point> queue = new LinkedList<>();
        Point start = new Point(0, 0, 0);
        visited[0][0] = true;
        queue.add(start);

        Map<Point, Point> parentMap = new HashMap<>();
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            final Point point = queue.poll();

            if (point.x == xDest && point.y == yDest) {
                List<Point> shortestPathPoints = new ArrayList<>();

                Point curr = point;
                while (curr != null) {
                    shortestPathPoints.add(0, curr);
                    curr = parentMap.get(curr);
                }
                return shortestPathPoints;
            }

            for (int i = 0; i < 4; i++) {
                int newX = point.x + xOffsets[i];
                int newY = point.y + yOffsets[i];

                if (isValid(grid, newX, newY) && !visited[newX][newY]) {
                    // BFS
                    visited[newX][newY] = true;
                    var p = new Point(newX, newY, point.distance + 1);
                    queue.add(p);
                    parentMap.put(p, point);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Point> placeStations(List<Point> shortestPath, int minimumDistance) {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < shortestPath.size(); i += minimumDistance) {
            points.add(shortestPath.get(i));
        }
        if (!points.contains(shortestPath.get(shortestPath.size() - 1))) {
            points.add(shortestPath.get(shortestPath.size() - 1));
        }
        return points;
    }

    @Override
    protected String describe() {
        return "Building radio stations in at most k distance to reach out to the end";
    }
}
