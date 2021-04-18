package org.kevin.builder;

public enum HairType {

    BALD("bald"),
    SHORT("short"),
    CURLY("curly"),
    LONG_STRAIGHT("long_straight"),
    LONG_CURLY("long_curly");

    private final String title;

    HairType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
