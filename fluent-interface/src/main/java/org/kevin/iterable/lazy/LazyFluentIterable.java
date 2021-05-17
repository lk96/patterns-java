package org.kevin.iterable.lazy;

import lombok.RequiredArgsConstructor;
import org.kevin.iterable.FluentIterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class LazyFluentIterable<E> implements FluentIterable<E> {

    private final Iterable<E> iterable;

    protected LazyFluentIterable() {
        iterable = this;
    }

    @Override
    public FluentIterable<E> filter(Predicate<? super E> predicate) {
        return new LazyFluentIterable<>() {
            @Override
            public Iterator<E> iterator() {
                return new DecoratingIterator<>(iterable.iterator()) {
                    @Override
                    public E computeNext() {
                        while (fromIterator.hasNext()) {
                            E candidate = fromIterator.next();
                            if (predicate.test(candidate)) {
                                return candidate;
                            }
                        }
                        return null;
                    }
                };
            }
        };
    }

    @Override
    public Optional<E> first() {
        Iterator<E> iterator = first(1).iterator();
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
    }

    @Override
    public FluentIterable<E> first(int count) {
        return new LazyFluentIterable<>() {
            @Override
            public Iterator<E> iterator() {
                return new DecoratingIterator<>(iterable.iterator()) {
                    int currentIndex;

                    @Override
                    public E computeNext() {
                        if (currentIndex < count && fromIterator.hasNext()) {
                            E candidate = fromIterator.next();
                            currentIndex++;
                            return candidate;
                        }
                        return null;
                    }
                };
            }
        };
    }

    @Override
    public Optional<E> last() {
        Iterator<E> iterator = last(1).iterator();
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
    }

    @Override
    public FluentIterable<E> last(int count) {
        return new LazyFluentIterable<>() {
            @Override
            public Iterator<E> iterator() {
                return new DecoratingIterator<E>(iterable.iterator()) {
                    private int stopIndex;
                    private int totalElementsCount;
                    private List<E> list;
                    private int currentIndex;

                    @Override
                    public E computeNext() {
                        initialize();
                        while (currentIndex < stopIndex && fromIterator.hasNext()) {
                            currentIndex++;
                            fromIterator.next();
                        }
                        if (currentIndex >= stopIndex && fromIterator.hasNext()) {
                            return fromIterator.next();
                        }
                        return null;
                    }

                    private void initialize() {
                        if (list == null) {
                            list = new ArrayList<>();
                            iterable.forEach(list::add);
                            totalElementsCount = list.size();
                            stopIndex = (totalElementsCount - count);
                        }
                    }
                };
            }
        };
    }

    @Override
    public <T> FluentIterable<T> map(Function<? super E, T> function) {
        return new LazyFluentIterable<>() {
            @Override
            public Iterator<T> iterator() {
                return new DecoratingIterator<T>(null) {
                    final Iterator<E> oldTypeIterator = iterable.iterator();

                    @Override
                    public T computeNext() {
                        if (oldTypeIterator.hasNext()) {
                            E candidate = oldTypeIterator.next();
                            return function.apply(candidate);
                        } else {
                            return null;
                        }
                    }
                };
            }
        };
    }

    @Override
    public List<E> asList() {
        return FluentIterable.copyList(iterable);
    }

    @Override
    public Iterator<E> iterator() {
        return new DecoratingIterator<E>(iterable.iterator()) {
            @Override
            public E computeNext() {
                return fromIterator.hasNext() ? fromIterator.next() : null;
            }
        };
    }

    public static <E> FluentIterable<E> from(Iterable<E> iterable) {
        return new LazyFluentIterable<>(iterable);
    }
}
