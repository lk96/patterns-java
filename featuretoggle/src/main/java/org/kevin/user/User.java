package org.kevin.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
