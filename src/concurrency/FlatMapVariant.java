package concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FlatMapVariant {

    public static void main(String[] args) {
        List<String> data = List.of("1,2,3", "4,5,6", "7,8,9");
        IntStream intStream = data.stream()
                .flatMapToInt(s -> Arrays.stream(s.split(","))
                        .mapToInt(Integer::parseInt));
        intStream.forEach(System.out::println); // 1 2 3 4 5 6 7 8 9

    }
}
