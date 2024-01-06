package concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ParallelProcessingAcceptAsync {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        List<CompletableFuture<Integer>> futures = data.stream()
                .map(num -> CompletableFuture.supplyAsync(() -> {
                    System.out.println("Computation Thread: " + Thread.currentThread().getName() + ", processing number: " + num);
                    return num * num;
                }, executor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenAcceptAsync(v -> {
                    System.out.println("Result processing Thread: " + Thread.currentThread().getName());
                    futures.stream()
                            .map(CompletableFuture::join)
                            .forEach(System.out::println);
                }, executor);

        allFutures.join(); // Wait for all futures including the result processing to complete
        executor.shutdown(); // Shut down the executor
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Execution Time: " + duration + " ms");
    }
}
