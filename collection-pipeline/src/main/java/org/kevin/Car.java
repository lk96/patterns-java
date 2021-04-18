package org.kevin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Car {

    private final String make;

    private final String model;

    private final int year;

    private final Category category;
}
