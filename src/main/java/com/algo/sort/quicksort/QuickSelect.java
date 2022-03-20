package com.algo.sort.quicksort;

/**
 * Do sorting algo using QuickSelect(Hoare's selection algorithm) algorithm to find kth smallest/biggest elements

 * Runtime complexity - O(n)
 * Space complexity - O(1)
 */
public class QuickSelect {

    public int quickSelect(int[] arr, int k) {
        return findKth(arr, 0, arr.length - 1, k);
    }

    private int partition(int[] arr, int low, int high) {
        int i = low;
        for(int j = low + 1; j <= high; j++) {
            if(arr[j] < arr[low]) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i, low);
        return i;
    }

    private int findKth(int[] arr, int low, int high, int k) {
        int pivot = partition(arr, low, high);

        if(pivot == k) {
            return arr[pivot];
        } else if(pivot > k - 1) {
            return findKth(arr, low, pivot - 1, k);
        } else {
            return findKth(arr, pivot + 1, high, k);
        }
    }

    private void swap(int[] arr, int index, int pivot) {
        int temp = arr[index];
        arr[index] = arr[pivot];
        arr[pivot] = temp;
    }

}