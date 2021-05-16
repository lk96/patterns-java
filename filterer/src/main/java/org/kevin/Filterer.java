package org.kevin;

import java.util.function.Predicate;

@FunctionalInterface
public interface Filterer<G,E>{

    G by(Predicate<? super E> predicate);
}
