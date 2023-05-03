package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Peter", "John", "Jane");
        System.out.println("Odd Indexed Names: " + getOddIndexedNames(names));

        System.out.println("Upper Sorted Desc: " + getUpperSortedDesc(names));

        String[] input = {"1, 2, 0", "4, 5"};
        System.out.println("Sorted and Joined Numbers: " + sortAndJoinNumbers(input));

        long a = 25214903917L;
        long c = 11L;
        long m = 1L << 48;
        long seed = 12345L;

        System.out.println("Linear Congruential Generator Numbers:");
        linearCongruentialGenerator(a, c, m, seed).limit(10).forEach(System.out::println);

        System.out.println("Task 5 method:");
        Stream<String> first = Stream.of("A", "C", "E");
        Stream<String> second = Stream.of("B", "D", "F");
        zip(first, second).forEach(System.out::println);
    }

    public static String getOddIndexedNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> (i + 1) + ". " + names.get(i))
                .collect(Collectors.joining(", "));
    }

    public static List<String> getUpperSortedDesc(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static String sortAndJoinNumbers(String[] input) {
        return Arrays.stream(input)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(Integer::parseInt)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public static Stream<Long> linearCongruentialGenerator(long a, long c, long m, long seed) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> secondIt = second.iterator();
        return first.flatMap(t -> secondIt.hasNext() ? Stream.of(t, secondIt.next()) : Stream.of(t));
    }
}
