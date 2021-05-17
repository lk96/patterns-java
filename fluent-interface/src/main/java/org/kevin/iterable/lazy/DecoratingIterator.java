package org.kevin.iterable.lazy;

import java.util.Iterator;

public abstract class DecoratingIterator<E> implements Iterator<E> {

    protected final Iterator<E> fromIterator;

    private E next;

    public DecoratingIterator(Iterator<E> fromIterator) {
        this.fromIterator = fromIterator;
    }

    @Override
    public boolean hasNext() {
        next = computeNext();
        return next != null;
    }

    @Override
    public E next() {
        if (next == null) {
            return fromIterator.next();
        } else {
            final E result = next;
            next = null;
            return result;
        }
    }

    public abstract E computeNext();
}
