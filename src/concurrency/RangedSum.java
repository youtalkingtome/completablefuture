package concurrency;

import java.util.stream.LongStream;

public class RangedSum {


    public static long rangedSum(long n) {
        long sum = 0;
        long startTime = System.nanoTime();
        sum = LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Execution Time: " + duration + " ms");
        return sum;
    }

    public static long parallelRangedSum(long n) {
        long sum = 0;
        long startTime = System.nanoTime();
        sum= LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Parallel Execution Time: " + duration + " ms");
        return sum;
    }


    public static void main(String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
                "12");
        long sum=rangedSum(10_000_000L);
        var parallelsum=parallelRangedSum( 10_000_000L);
    }
}
