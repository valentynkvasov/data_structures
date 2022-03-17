package com.algo.merge_sort;

import java.util.Arrays;

/**
 * Do sorting algo using MergeSort algorithm
 * <p>
 * Runtime complexity - O(n * log(n))
 * Space complexity - O(n)
 */
public class MergeSort {

    public void mergeSort(int[] arr) {
        divide(arr, arr.length);
    }

    private void divide(int[] arr, int len) {
        if (arr.length < 2) {
            return;
        }
        int mid = len / 2;
        int[] leftArr = new int[mid];
        int[] rightArr = new int[len - mid];

        for(int i = 0; i < mid; i++) {
            leftArr[i] = arr[i];
        }
        for(int i = mid, j = 0; i < arr.length; i++, j++) {
            rightArr[j] = arr[i];
        }
        divide(leftArr, mid);
        divide(rightArr, len - mid);

        merge(arr, leftArr, rightArr);
    }

    private void merge(int[] arr, int[] leftArr, int[] rightArr) {
        int k = 0, i = 0, j = 0;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] < rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {1, 4, 2, 56, 2, 4, 2, 1, 3};
        mergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
