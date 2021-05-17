package org.kevin.iterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FluentIterable<E> extends Iterable<E> {

    FluentIterable<E> filter(Predicate<? super E> predicate);

    Optional<E> first();

    FluentIterable<E> first(int count);

    Optional<E> last();

    FluentIterable<E> last(int count);

    <T> FluentIterable<T> map(Function<? super E, T> function);

    List<E> asList();

    static <E> List<E> copyList(Iterable<E> iterable) {
        ArrayList<E> copy = new ArrayList<>();
        iterable.forEach(copy::add);
        return copy;
    }
}
