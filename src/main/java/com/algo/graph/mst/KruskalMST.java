package com.algo.graph.mst;

import com.structure.disjointset.UnionFindListImpl;

import java.util.Arrays;
import java.util.Comparator;

public class KruskalMST {

    /**
           [5]
         1----2
         | \   \ [6]
     [3] |  \[2] 5
         |   \  / [2]
         3----4
          [9]

     MST
          [5]
         1----2
         | \
     [3] |  \[2] 5
         |   \  / [2]
         3    4

     */

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{1,2,5},{2,5,6},{1,4,2},{3,4,9},{4,5,2},{1,3,3}};
        UnionFindListImpl uf = new UnionFindListImpl();

        for(int i = 1; i <= n; i++) {
            uf.makeSet(i);
        }

        Arrays.sort(edges, Comparator.comparingInt(x -> x[2]));

        int mst = 0;
        for(int[] edge : edges) {
            if(uf.union(edge[0], edge[1])) {
                mst += edge[2];
            }
        }

        System.out.println(mst);
    }
}
