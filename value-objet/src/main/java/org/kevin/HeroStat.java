package org.kevin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class HeroStat {

    private final int strength;

    private final int intelligence;

    private final int luck;

    public static HeroStat valueOf(int strength, int intelligence, int luck) {
        return new HeroStat(strength, intelligence, luck);
    }
}
