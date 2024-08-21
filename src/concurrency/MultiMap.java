package concurrency;

import java.util.List;
import java.util.stream.Collectors;

public class MultiMap {
    public static void main(String[] args) {
        List<String> strings = List.of("a", "b", "c");

        List<String> result = strings.stream()
                .<String>mapMulti((s, consumer) -> {
                    consumer.accept(s.toUpperCase());
                    consumer.accept(s.toLowerCase());
                })
                .collect(Collectors.toList()); // [A, a, B, b, C, c]

    }
}
