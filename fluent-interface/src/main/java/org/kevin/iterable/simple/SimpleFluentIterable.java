package org.kevin.iterable.simple;

import lombok.RequiredArgsConstructor;
import org.kevin.iterable.FluentIterable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class SimpleFluentIterable<E> implements FluentIterable<E> {

    private final Iterable<E> iterable;

    @Override
    public FluentIterable<E> filter(Predicate<? super E> predicate) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E nextElement = iterator.next();
            if (!predicate.test(nextElement)) {
                iterator.remove();
            }
        }
        return this;
    }

    @Override
    public Optional<E> first() {
        Iterator<E> iterator = first(1).iterator();
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
    }

    @Override
    public FluentIterable<E> first(int count) {
        Iterator<E> iterator = iterator();
        int currentCount = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (currentCount >= count) {
                iterator.remove();
            }
            currentCount++;
        }
        return this;
    }

    @Override
    public Optional<E> last() {
        List<E> list = last(1).asList();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }

    @Override
    public FluentIterable<E> last(int count) {
        int remainingElementsCount = getRemainingElementsCount();
        Iterator<E> iterator = iterator();
        int currentIndex = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (currentIndex < remainingElementsCount - count) {
                iterator.remove();
            }
            currentIndex++;
        }
        return this;
    }

    @Override
    public <T> FluentIterable<T> map(Function<? super E, T> function) {
        ArrayList<T> temporaryList = new ArrayList<>();
        this.forEach(e -> temporaryList.add(function.apply(e)));
        return from(temporaryList);
    }

    @Override
    public List<E> asList() {
        return toList(iterable.iterator());
    }

    public static <E> FluentIterable<E> from(Iterable<E> iterable) {
        return new SimpleFluentIterable<>(iterable);
    }

    public static <E> FluentIterable<E> fromCopyOf(Iterable<E> iterable) {
        List<E> copy = FluentIterable.copyList(iterable);
        return new SimpleFluentIterable<>(copy);
    }

    @Override
    public Iterator<E> iterator() {
        return iterable.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        iterable.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return iterable.spliterator();
    }

    public final int getRemainingElementsCount() {
        int counter = 0;
        for (E ignored : this) {
            counter++;
        }
        return counter;
    }

    public static <E> List<E> toList(Iterator<E> iterator) {
        ArrayList<E> copy = new ArrayList<>();
        iterator.forEachRemaining(copy::add);
        return copy;
    }
}
