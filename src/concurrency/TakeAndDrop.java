package concurrency;

import java.util.List;
import java.util.stream.Collectors;

public class TakeAndDrop {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> taken = numbers.stream()
                .takeWhile(n -> n < 5).peek((n)-> System.out.println("Take While"+n))
                .collect(Collectors.toList());

        List<Integer> dropped = numbers.stream()
                .dropWhile(n -> n < 5).peek((n)-> System.out.println("Drop While"+n))
                .collect(Collectors.toList());


    }
}
