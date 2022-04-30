package com.algo.graph.cycle;

import java.util.*;

public class DirectedGraphCycle {

    private static Map<Integer, List<Integer>> graph;
    private static int size = 6;

    public static void main(String[] args) {
        graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Collections.singletonList(3));
        graph.put(2, Collections.singletonList(4));
        graph.put(3, Arrays.asList(4, 5));
        graph.put(4, Collections.singletonList(5));
        graph.put(5, Collections.emptyList());

        Set<Integer> initSet = new HashSet<>();
        Set<Integer> visitingSet = new HashSet<>();
        Set<Integer> visitedSet = new HashSet<>();

        for(int i = 0; i < size; i++) {
            initSet.add(i);
        }

        while (!initSet.isEmpty()) {
            Integer node = initSet.iterator().next();
            if(isCycle(node, initSet, visitingSet, visitedSet)) {
                System.out.println("Graph has a cycle");
                return;
            }
        }
        System.out.println("Graph doesn't have a cycle");
    }

    public static boolean isCycle(int src, Set<Integer> initSet, Set<Integer> visitingSet, Set<Integer> visitedSet) {
        if(visitingSet.contains(src)) {
            return true;
        }

        initSet.remove(src);
        visitingSet.add(src);

        for(int node : graph.getOrDefault(src, new ArrayList<>())) {
            if(visitedSet.contains(node)) {
                continue;
            }
            if(isCycle(node, initSet, visitingSet, visitedSet)) {
                return true;
            }
        }

        visitingSet.remove(src);
        visitingSet.add(src);

        return false;
    }
}
