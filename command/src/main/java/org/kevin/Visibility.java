package org.kevin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Visibility {

    VISIBLE("visible"),
    INVISIBLE("invisible");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
