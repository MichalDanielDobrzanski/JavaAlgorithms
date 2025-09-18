package org.algorithms.graph;

import java.util.*;

/**
 * Representing graph as adjList, to work on edges, to have running complexity of O(E * logV)
 * Better to use adjList, as otherwise it would be O(V^2 * logV), and there can be
 * much more vertices than edges
 */
public class DijkstrasAlgorithm {

    private record Edge(
            int to,
            int weight
    ) {
    }

    private record Node(
            int v,
            int dist
    ) {
    }

    // Assumes 0-based node indexing, and a directed graph
    // weights[x] - a 3 element array - [from, to, weight]
    public int[] dijkstra(int n, int startNode, int[][] weights) {
        // build adj list
        List<List<Edge>> adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] ws : weights) adjList.get(ws[0]).add(new Edge(ws[1], ws[2]));

        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;

        boolean[] visited = new boolean[n];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.dist));
        pq.add(new Node(startNode, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visited[curr.v]) continue;
            visited[curr.v] = true;

            // optimisation
            if (curr.dist > distances[curr.v]) continue;

            for (Edge edge : adjList.get(curr.v)) {
                // edge relaxation
                if (distances[edge.to] > distances[curr.v] + edge.weight) {
                    distances[edge.to] = distances[curr.v] + edge.weight;
                    pq.add(new Node(edge.to, distances[edge.to]));
                }
            }
        }

        return distances;
    }
}
