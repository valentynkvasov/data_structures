package com.algo.graph;

import java.util.*;

public class TopologicalSort {

    private static Map<Integer, List<Integer>> graph;
    private static int size = 6;

    public static void main(String[] args) {
        graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Collections.singletonList(3));
        graph.put(2, Collections.singletonList(4));
        graph.put(3, Arrays.asList(4, 5));
        graph.put(4, Collections.singletonList(5));
        graph.put(5, Collections.singletonList(0));

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> sorted = new Stack<>();

        for (int node = 0; node < size; size++) {
            if (visited.contains(node)) {
                continue;
            }
            topSort(node, visited, sorted);
        }

        while (!sorted.isEmpty()) {
            System.out.println(sorted.pop() + " ");
        }
    }

    private static void topSort(int src, Set<Integer> visited, Stack<Integer> sorted) {
        visited.add(src);

        for(int node : graph.getOrDefault(src, new ArrayList<>())) {
            if(visited.contains(node)) {
                continue;
            }
            topSort(node, visited, sorted);
        }
        sorted.push(src);
    }

}
