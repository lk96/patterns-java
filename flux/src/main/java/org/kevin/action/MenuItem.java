package org.kevin.action;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MenuItem {
    HOME("Home"), PRODUCTS("Products"), COMPANY("Company");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
