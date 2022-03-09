package com.structure.pq;

import java.util.*;
import java.util.stream.Collectors;

public class PriorityQueue<T extends Comparable<T>> {

    private T[] pq;
    private int pointer;

    PriorityQueue(int initCapacity) {
        pq = (T[]) new Comparable[initCapacity + 1];
        pointer = 0;
    }

    public void add(T element) {
        pq[++pointer] = element;
        swim(pointer);
    }

    public T deletePeek(){
        T temp = (T) pq[1];
        exch(1, pointer--);
        sink(1);
        pq[pointer + 1] = null;
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(maxUniqueSplit("abacdec"));
    }

    public static int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        return dfs(set, 0, s);
    }

    private static int dfs(Set<String> set, int idx, String s) {
        if (idx >= s.length()) return 0;
        int res = Integer.MAX_VALUE;  // did not find method to split;
        for (int i = idx + 1; i <= s.length(); i++) {
            String sub = s.substring(idx, i);
            if (!set.add(sub)) continue; //already contains sub
            int next = dfs(set, i, s);
            if (next >= 0) res = Math.min(res, next + 1);
            set.remove(sub);
        }
        return res;
    }

    private void sink(int k) {
        while (2 * k <= pointer) {
            int j = 2 * k;
            if(j < pointer && less(j, j + 1)) {
                j++;
            }
            if(!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void exch(int k, int i) {
        T temp = pq[k];
        pq[k] = pq[i];
        pq[i] = temp;
    }

    public void sort() {
        int size = pq.length;
        for(int i = size / 2; i >= 1; i--) {
            sink(i);
        }
    }

}