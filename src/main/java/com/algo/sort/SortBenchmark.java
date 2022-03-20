package com.algo.sort;

import com.algo.sort.mergesort.MergeSort;
import com.algo.sort.quicksort.QuickSort;
import org.apache.commons.lang3.RandomUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 SortBenchmark.mergeSortCheck        10000           5  avgt    5    1.754 ±  0.065  ms/op
 SortBenchmark.quickSortCheck        10000           5  avgt    5  246.553 ± 13.240  ms/op
 */

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xmx500m", "-Xms500m"})
public class SortBenchmark {

    @Param({"10000"})
    private int arraySize;

    @Param({"5"})
    private int dataSize;

    private static final QuickSort QUICK_SORT = new QuickSort();
    private static final MergeSort MERGE_SORT = new MergeSort();
    private static final List<int[]> DATA_FOR_TESTING = new ArrayList<>();

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(com.algo.sort.SortBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setUp() {
        for(int i = 0; i < dataSize; i++) {
            int[] arr = new int[arraySize];
            for (int j = 0; j < arraySize; j++) {
                arr[j] = RandomUtils.nextInt(0, Integer.MAX_VALUE);
            }
            DATA_FOR_TESTING.add(arr);
        }
    }

    @Benchmark
    public void quickSortCheck(Blackhole bh) {
        for (int[] arr : DATA_FOR_TESTING) {
            QUICK_SORT.sort(arr);
        }
    }

    @Benchmark
    public void mergeSortCheck(Blackhole bh) {
        for (int[] arr : DATA_FOR_TESTING) {
            MERGE_SORT.sort(arr);
        }
    }

}