package com.algo.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Return random index/value for big lists with unknown size using ReservoirSampling algorithm in O(1) space complexity

 * Runtime complexity - O(n)
 * Space complexity - O(1)
 */
public class ReservoirSampling {

    private List<Integer> list;

    public ReservoirSampling(List<Integer> list) {
        this.list = list;
    }

    /**
     *
     * @param val
     * @return random index for target value
     */
    public int getRandomIndex(int val) {
        int scope = 1, index = 1;

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) == val){
                if(1.0d / scope > Math.random()) {
                    index = i;
                }
                scope++;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        ReservoirSampling sampling = new ReservoirSampling(Arrays.asList(1,1,2,3,4,1,1,3,2));

        System.out.println(sampling.getRandomIndex(1));
        System.out.println(sampling.getRandomIndex(1));
        System.out.println(sampling.getRandomIndex(1));
        System.out.println(sampling.getRandomIndex(1));
        System.out.println(sampling.getRandomIndex(1));
        System.out.println(sampling.getRandomIndex(3));
        System.out.println(sampling.getRandomIndex(3));
        System.out.println(sampling.getRandomIndex(3));
    }
}
