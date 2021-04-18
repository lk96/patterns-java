package org.kevin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Student {

    private final Integer id;
    private final String name;
    private final String address;
}
