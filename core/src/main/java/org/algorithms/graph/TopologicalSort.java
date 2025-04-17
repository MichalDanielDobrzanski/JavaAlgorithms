package org.algorithms.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Topological sort algorithm running in O(V + E), with cycle detection.
 * Detecting the cycle should stop (early return) the searched branch and
 * return false to the caller.
 */
public class TopologicalSort {

    public int[] topSort(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] es : edges) adjList.get(es[0]).add(es[1]);

        boolean[] visited = new boolean[n];
        // to detect potential cycles
        boolean[] inCurrentPath = new boolean[n];

        // top sorted queue, to be converted to array later on
        Deque<Integer> result = new LinkedList<>();

        for (int node = 0; node < n; node++) {
            if (!dfs(node, adjList, visited, inCurrentPath, result)) return new int[0];
        }

        return result.stream().mapToInt(e -> e).toArray();
    }

    private boolean dfs(int node, List<List<Integer>> adjList, boolean[] visited, boolean[] inCurrentPath, Deque<Integer> result) {
        // cycle detected
        if (inCurrentPath[node]) return false;
        if (visited[node]) return true;

        inCurrentPath[node] = true;

        visited[node] = true;

        for (int nei : adjList.get(node)) {
            if (!dfs(nei, adjList, visited, inCurrentPath, result)) return false;
        }

        inCurrentPath[node] = false;

        result.addFirst(node);
        return true;
    }

}
