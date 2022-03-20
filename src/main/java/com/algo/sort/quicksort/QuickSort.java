package com.algo.sort.quicksort;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Do sorting algo using QuickSort algorithm

 * Runtime complexity - O(n * log(n))
 * Space complexity - O(1)
 */

/*
0 1 2    3  4 5 6 7
7 4 1534 2 12 5 2 5

1. 7 4 1534 2 12 5 2 5
2. 7 4 2 1534 12 5 2 5
3. 7 4 2 5 12 1534 2 5
4. 7 4 2 5 2 1534 12 5
5. 7 4 2 5 2 5 12 1534
5. 5 4 2 5 2 7 12 1534
 */

public class QuickSort {

    private int partition(int[] arr, int low, int high) {
        int j = low;
        for(int i = low + 1; i <= high; i++) {
            if(arr[i] < arr[low]) {
                swap(arr, ++j, i);
            }
        }
        swap(arr, low, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void sort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }

        int pivot = partition(arr, low, high);

        sort(arr, low, pivot - 1);
        sort(arr, pivot + 1, high);
    }

    public void sort(int[] arr) {
        this.sort(arr, 0, arr.length - 1);
    }

}
