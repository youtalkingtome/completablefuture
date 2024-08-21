package concurrency;

import java.util.stream.Stream;

public class InfiniteStream {

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2);
        stream.limit(5).forEach(System.out::println); // 0, 2, 4, 6, 8

    }
}
