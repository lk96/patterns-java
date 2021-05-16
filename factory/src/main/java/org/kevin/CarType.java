package org.kevin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@Getter
@RequiredArgsConstructor
public enum CarType {

    FORD(Ford::new),
    FERRARI(Ferrari::new);

    private final Supplier<Car> constructor;
}
