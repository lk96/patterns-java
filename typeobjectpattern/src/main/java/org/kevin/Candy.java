package org.kevin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PACKAGE)
public class Candy {

    enum Type {
        CRUSHABLE_CANDY,
        REWARD_FRUIT
    }

    String name;

    Candy parent;

    String parentName;

    public Candy(String name, String parentName, Type type, int points) {
        this.name = name;
        this.parentName = parentName;
        this.points = points;
        this.type = type;
    }

    @Setter
    private int points;

    private final Type type;


}
