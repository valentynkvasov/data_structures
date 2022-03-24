package com.algo.string;


import com.algo.string.kmp.KMP;
import com.algo.string.naive.NaiveStringComparison;
import com.algo.string.rabinkarp.RabinKarp;
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

 Benchmark                        (N)  Mode  Cnt    Score   Error  Units
 StringBenchmark.kmpCheck         10  avgt    5  0.005 ±  0.001  ms/op
 StringBenchmark.naiveCheck       10  avgt    5  0.034 ±  0.001  ms/op
 StringBenchmark.rabinKarpCheck   10  avgt    5  0.384 ±  0.009  ms/op

 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xmx500m"})
public class StringBenchmark {

    @Param({"12"})
    private int N;

    private static final KMP kmp = new KMP();
    private static final RabinKarp rabinKarp = new RabinKarp();
    private static final NaiveStringComparison naive = new NaiveStringComparison();
    private static final List<String> DATA_FOR_TESTING = new ArrayList<>();
    private static final String pattern = RandomStringUtils.random(25, true, false);

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(StringBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setUp() {
        for(int i = 0, size = 30; i < N; size*=2, i++) {
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

    @Benchmark
    public void rabinKarpCheck(Blackhole bh) {
        for (String str : DATA_FOR_TESTING) {
            rabinKarp.searchPattern(str, pattern);
        }
    }
}
