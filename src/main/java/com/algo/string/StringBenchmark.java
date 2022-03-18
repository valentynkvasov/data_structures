package com.algo.string;


import com.algo.string.kmp.KMP;
import com.algo.string.naive.NaiveStringComparison;
import org.apache.commons.lang3.RandomStringUtils;
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

Benchmark                   (N)  Mode  Cnt    Score   Error  Units
StringBenchmark.kmpCheck     12  avgt    5   46.406 ± 5.982  ms/op
StringBenchmark.naiveCheck   12  avgt    5  207.723 ± 4.941  ms/op

 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 5, jvmArgs = {"-Xmx500m"})
public class StringBenchmark {

    @Param({"10"})
    private int N;

    private static final KMP kmp = new KMP();
    private static final NaiveStringComparison naive = new NaiveStringComparison();
    private static final List<String> DATA_FOR_TESTING = new ArrayList<>();
    private static final String pattern = RandomStringUtils.random(20, true, false);

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(StringBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setUp() {
        for(int i = 0, size = 20; i < N; size*=2, i++) {
            DATA_FOR_TESTING.add(RandomStringUtils.random(size, true, false));
        }
    }

    @Benchmark
    public void kmpCheck(Blackhole bh) {
        for (String str : DATA_FOR_TESTING) {
            kmp.searchPattern(str, pattern);
        }
    }

    @Benchmark
    public void naiveCheck(Blackhole bh) {
        for (String str : DATA_FOR_TESTING) {
            naive.searchPattern(str, pattern);
        }
    }
}
