package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.kevin.iterable.lazy.LazyFluentIterable;
import org.kevin.iterable.simple.SimpleFluentIterable;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
public class App {

    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, -62, 18, -22, 15, -98, 8, 35, -86, 28, -89, 78, 36, 66, -80);
        prettyPrint("The initial list contains:", integerList);

        List<Integer> firstFiveNegatives = SimpleFluentIterable.fromCopyOf(integerList)
                .filter(negatives())
                .first(3)
                .asList();
        prettyPrint("The first three negative values are: ", firstFiveNegatives);

        List<Integer> lastTwoPositives = SimpleFluentIterable.fromCopyOf(integerList)
                .filter(positives())
                .last(2)
                .asList();
        prettyPrint("The last two positive values are:", lastTwoPositives);

        SimpleFluentIterable.fromCopyOf(integerList)
                .filter(number -> (number & 2) == 1)
                .first()
                .ifPresent(evenNumber -> log.info("The first even number is: {}", evenNumber));

        List<String> transformedList = SimpleFluentIterable.fromCopyOf(integerList)
                .filter(negatives())
                .map(transformToString())
                .asList();
        prettyPrint("A string-mapped list of negative numbers contains:", transformedList);

        List<String> lastTwoOfFirstStringMapped = LazyFluentIterable.from(integerList).filter(positives())
                .first(1)
                .last(2)
                .map(number -> "String[" + number + "]")
                .asList();
        prettyPrint("The lazy list contains the last two of the first four positive numbers mapped to Strings:", lastTwoOfFirstStringMapped);

        LazyFluentIterable.from(integerList).filter(negatives()).first(2).last()
                .ifPresent(number -> log.info("Last amongst first two negatives: {}", number));
    }

    private static Function<Integer, String> transformToString() {
        return integer -> "String [" + integer + "]";
    }

    private static Predicate<? super Integer> negatives() {
        return integer -> integer < 0;
    }

    private static Predicate<? super Integer> positives() {
        return integer -> integer > 0;
    }

    private static <E> void prettyPrint(String prefix, Iterable<E> iterable) {
        prettyPrint(",", prefix, iterable);
    }

    private static <E> void prettyPrint(String delimiter, String prefix, Iterable<E> iterable) {
        StringJoiner joiner = new StringJoiner(delimiter, prefix, ".");
        iterable.forEach(e -> joiner.add(e.toString()));
        log.info(joiner.toString());
    }
}
