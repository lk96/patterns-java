package org.kevin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UnitActions {
    INSERT("INSERT"),
    DELETE("DELETE"),
    MODIFY("MODIFY");

    private final String actionValue;
}