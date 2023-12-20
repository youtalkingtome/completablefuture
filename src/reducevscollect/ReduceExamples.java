package reducevscollect;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.IntStream.concat;


public class ReduceExamples {

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 5, 1, 9, 6};
        int[] arr2 = new int[]{4, 6, 2, 1, 2};

        int[] mergedArray = concat(Arrays.stream(arr1),Arrays.stream(arr2))
                .sorted()
                .toArray();

        System.out.println(Arrays.toString(mergedArray));
        //[1, 1, 2, 2, 3, 4, 5, 6, 6, 9]
        int n = 5;
        BigInteger[] fibonacci = Stream.iterate(new BigInteger[]{BigInteger.ZERO, BigInteger.ONE},
                        f -> new BigInteger[]{f[1], f[0].add(f[1])})
                .limit(n)
                .reduce((a, b) -> b).orElse(new BigInteger[]{});
        BigInteger fibonaccitoN = fibonacci[0];
        System.out.print(fibonaccitoN);

        List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0,6.0,7.0);
        double mean = numbers.stream().reduce(0.0, Double::sum) / numbers.size();
        double variance = numbers.stream()
                .reduce(0.0,
                        (acc, num) -> acc + Math.pow(num - mean, 2),
                        Double::sum) / numbers.size();
        double standardDeviation = Math.sqrt(variance);

        //building a histogram
        List<Integer> histoGramNumbers = Arrays.asList(1, 2, 1, 3, 3, 3, 4,8,9,45,67,2);
        Map<Integer, Integer> histogramExample = histoGramNumbers.stream()
                .reduce(new HashMap<Integer, Integer>(),
                        (map, val) -> { map.merge(val, 1, Integer::sum); return map; },
                        (map1, map2) -> { map1.putAll(map2); return map1; });


        List<List<Integer>> nestedList=Arrays.asList(Arrays.asList(1,2,3,6,7),
                Arrays.asList(8,5,6));
        int sumOfElemetnsInNestedLists = nestedList.stream()
                .reduce(Collections.emptyList(),   //Edge Case for reduce Method
                        (partialResult, item) -> {
                            List<Integer> newList = new ArrayList<>(partialResult);//Creating a new list with the nested lists
                            newList.addAll(item); // Adding all items of the nested lists in to the big list
                            return newList; //returning the resultant flattened list.
                        })
                .stream()
                .reduce(0, Integer::sum);// Summing the elements of Flattened list.






    }

}
