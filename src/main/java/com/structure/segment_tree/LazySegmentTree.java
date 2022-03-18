package com.structure.segment_tree;

public class LazySegmentTree {

    private final int[] arr;
    private final int[] st;
    private final int[] lazy;

    public LazySegmentTree(int[] arr) {
        this.arr = arr;

        int treeSize = getNextPowerOfTwo(arr.length);
        this.lazy = new int[treeSize * 2 - 1];
        this.st = new int[treeSize * 2 - 1];
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

        if(lazy[index] != 0) {
            st[index] += lazy[index];
            if(low != high) {
                lazy[2 * index + 1] += lazy[index];
                lazy[2 * index + 2] += lazy[index];
            }
            lazy[index] = 0;
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
        updateRangeQuery(index, index, 0, arr.length - 1, 0, val);
    }

    private void updateRangeQuery(int qlow, int qhigh, int low, int high, int index, int val) {
        if(lazy[index] != 0) {
            st[index] += lazy[index];
            if(low != high) {
                lazy[2 * index + 1] += lazy[index];
                lazy[2 * index + 2] += lazy[index];
            }
            lazy[index] = 0;
        }

        if (low > high || low > qhigh || high < qlow)
            return;                                             // out of range. escape.

        if (qlow <= low && high <= qhigh) {
            st[index] = val;

            if(low != high) {
                lazy[2 * index + 1] += val;
                lazy[2 * index + 2] += val;
            }
            return;
        }
        int mid = getMid(low, high);
        updateRangeQuery(qlow, qhigh, low, mid, 2 * index + 1, val);
        updateRangeQuery(qlow, qhigh, mid + 1, high, 2 * index + 2, val);

        st[index] = st[2 * index + 1] + st[2 * index + 2];
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
        LazySegmentTree tree = new LazySegmentTree(arr);

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
