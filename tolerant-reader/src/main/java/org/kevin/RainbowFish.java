package org.kevin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class RainbowFish implements Serializable {

    @Serial
    private static final long serialVersionUID = 7002398792646188489L;

    private final String name;

    private final int age;

    private final int lengthMeters;

    private final int weightTons;
}
