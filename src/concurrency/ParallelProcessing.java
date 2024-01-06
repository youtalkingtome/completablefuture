package concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ParallelProcessing {

    public static void main(String args[]) {
        long startTime = System.nanoTime();
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        List<CompletableFuture<Integer>> futures = data.stream()
                .map(num -> CompletableFuture.supplyAsync(() -> {
                    // Print the thread name here
                    System.out.println("Thread name: " + Thread.currentThread().getName() + ", processing number: " + num);
                    return num * num;
                }))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
                .thenAccept(System.out::println);

        // Waiting for all futures to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Execution Time: " + duration + " ms");

    }
}
