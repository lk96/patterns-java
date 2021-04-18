package org.kevin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Finder {

    List<String> find(String text);

    static Finder contains(String world) {
        return text -> Stream.of(text.split("\n"))
                .filter(line -> line.toLowerCase().contains(world.toLowerCase()))
                .collect(Collectors.toList());
    }

    default Finder not(Finder notFinder) {
        return text -> {
            List<String> res = this.find(text);
            res.removeAll(notFinder.find(text));
            return res;
        };
    }

    default Finder or(Finder orFinder) {
        return text -> {
            List<String> res = this.find(text);
            res.addAll(orFinder.find(text));
            return res;
        };
    }

    default Finder and(Finder andFinder) {
        return text -> this.find(text).stream().
                flatMap(line -> andFinder.find(line).stream())
                .collect(Collectors.toList());
    }
}
