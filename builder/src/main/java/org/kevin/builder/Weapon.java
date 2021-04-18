package org.kevin.builder;

import java.util.Locale;

public enum Weapon {

    DAGGER,SWORD,AXE,WARHAMMER,BOW;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
