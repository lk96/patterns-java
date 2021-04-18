package org.kevin;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Finders {

    private Finders() {
    }

    public static Finder advancedFinder(String query, String orQuery, String notQuery) {
        return Finder.contains(query).or(Finder.contains(orQuery))
                .not(Finder.contains(notQuery));
    }

    public static Finder filteredFinder(String query, String... excludeQueries) {
        Finder finder = Finder.contains(query);
        for (String excludeQuery : excludeQueries) {
            finder = finder.not(Finder.contains(excludeQuery));
        }
        return finder;
    }

    public static Finder specializedFinder(String... queries) {
        Finder finder = identMulti();
        for (String query : queries) {
            finder = finder.and(Finder.contains(query));
        }
        return finder;
    }

    public static Finder expandedFinder(String... queries) {
        Finder finder = identSum();
        for (String query : queries) {
            finder = finder.or(Finder.contains(query));
        }
        return finder;
    }

    public static Finder identMulti() {
        return text -> Stream.of(text.split("\n")).collect(Collectors.toList());
    }

    public static Finder identSum(){
        return text -> new ArrayList<>();
    }
}
