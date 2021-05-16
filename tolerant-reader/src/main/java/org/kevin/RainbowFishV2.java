package org.kevin;

import lombok.Getter;

import java.io.Serial;

@Getter
public class RainbowFishV2 extends RainbowFish {

    @Serial
    private static final long serialVersionUID = 5007531876031844820L;

    private boolean sleeping;

    private boolean hungry;

    private boolean angry;


    public RainbowFishV2(String name, int age, int lengthMeters, int weightTons) {
        super(name, age, lengthMeters, weightTons);
    }

    public RainbowFishV2(String name, int age, int lengthMeters, int weightTons, boolean sleeping, boolean hungry, boolean angry) {
        this(name, age, lengthMeters, weightTons);
        this.sleeping = sleeping;
        this.hungry = hungry;
        this.angry = angry;
    }
}
