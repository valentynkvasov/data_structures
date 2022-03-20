package com.algo.sort;

import com.algo.sort.quicksort.QuickSelect;
import org.apache.commons.lang3.RandomUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 KthBenchmark.pqCheck                 10000           5  avgt    5   2.229 ± 0.067  ms/op
 KthBenchmark.quickSelectCheck        10000           5  avgt    5  53.884 ± 4.255  ms/op
 */

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xmx500m", "-Xms500m"})
public class KthBenchmark {

    @Param({"10000"})
    private int arraySize;

    @Param({"5"})
    private int dataSize;

    private static final QuickSelect QUICK_SELECT = new QuickSelect();

    private static final List<int[]> DATA_FOR_TESTING = new ArrayList<>();
    private static final List<Integer> DATA_FOR_TESTING_K = new ArrayList<>();

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(KthBenchmark.class.getSimpleName())
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
            DATA_FOR_TESTING_K.add(RandomUtils.nextInt(0, arr.length));
        }
    }

    @Benchmark
    public void quickSelectCheck(Blackhole bh) {
        Iterator<Integer> itr = DATA_FOR_TESTING_K.iterator();
        for (int[] arr : DATA_FOR_TESTING) {
            QUICK_SELECT.quickSelect(arr, arr.length - itr.next());
        }
    }

    @Benchmark
    public void pqCheck(Blackhole bh) {
        Iterator<Integer> itr = DATA_FOR_TESTING_K.iterator();

        for (int[] arr : DATA_FOR_TESTING) {
            Queue<Integer> PQ = new PriorityQueue<>();
            int k = itr.next();

            for(int num : arr) {
                PQ.add(num);

                if(PQ.size() > k) {
                    PQ.poll();
                }
            }
        }
    }

}