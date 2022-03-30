package com.structure.segment_tree;

//https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/
public class SegmentTree {

    private final int[] arr;
    private final int[] st;

    public SegmentTree(int[] arr) {
        this.arr = arr;
        this.st = new int[getNextPowerOfTwo(arr.length) * 2 - 1];
    }

    public void buildSegmentTree() {
        buildSegmentTree(0, arr.length - 1, 0);
    }

    private void buildSegmentTree(int low, int high, int index) {
        if (low == high) {
            st[index] = arr[low];
            return;
        }
        int mid = getMid(low, high);
        buildSegmentTree(low, mid, 2 * index + 1);
        buildSegmentTree(mid + 1, high, 2 * index + 2);
        st[index] = st[2 * index + 1] + st[2 * index + 2];
    }

    public int getRangeQuery(int qlow, int qhigh){
        return getRangeQuery(qlow, qhigh, 0, arr.length - 1, 0);
    }

    /*
       arr = [0..9], q = [1,3], lh = [5,9]  -- qhigh < low
       arr = [0..9], q = [5,9], lh = [1,3]  -- qlow > high
    */
    private int getRangeQuery(int qlow, int qhigh, int low, int high, int index) {
        if (qhigh < low || high < qlow) { //no overlap
            return 0;
        }
        if (qlow <= low && high <= qhigh) { //full overlap
            return st[index];
        }
        int mid = getMid(low, high);
        return getRangeQuery(qlow, qhigh, low, mid, 2 * index + 1)
                + getRangeQuery(qlow, qhigh, mid + 1, high, 2 * index + 2);
    }

    public void updateRangeQuery(int index, int val) {
        arr[index] = val;
        updateRangeQuery(0, arr.length - 1, 0, index);
    }

    private void updateRangeQuery(int low, int high, int segmentIndex, int index) {
        if (low == high) {
            st[segmentIndex] = arr[low];
            return;
        }
        int mid = getMid(low, high);

        if (index <= mid) {
            updateRangeQuery(low, mid, 2 * segmentIndex + 1, index);
        } else {
            updateRangeQuery(mid + 1, high, 2 * segmentIndex + 2, index);
        }

        st[segmentIndex] = st[2 * segmentIndex + 1] + st[2 * segmentIndex + 2];
    }

    private int getNextPowerOfTwo(int len) {
        if ((len & (len - 1)) == 0) {
            return len;
        }
        int power = 1;
        while (power < len) {
            power <<= 1;
        }
        return power;
    }

    private int getMid(int low, int high) {
        return (low + high) / 2;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, -3, 2, -10, 3, 4};
        //prefix sum 1  3  6  10  7  9  -1   2  6
        //indexes    0  1  2  3  4   5   6   7  8
        SegmentTree tree = new SegmentTree(arr);

        tree.buildSegmentTree();

        int sum = tree.getRangeQuery(0, 8);
        System.out.println(sum == 6);

        sum = tree.getRangeQuery(0, 0);
        System.out.println(sum == 1);

        sum = tree.getRangeQuery(8, 8);
        System.out.println(sum == 4);

        sum = tree.getRangeQuery(3, 6);
        System.out.println(sum == -7);

        tree.updateRangeQuery(0, 2);

        sum = tree.getRangeQuery(0, 8);
        System.out.println(sum == 7);

        tree.updateRangeQuery(4, -100);

        sum = tree.getRangeQuery(0, 3);
        System.out.println(sum == 11);

        sum = tree.getRangeQuery(0, 4);
        System.out.println(sum == -89);
    }
}
