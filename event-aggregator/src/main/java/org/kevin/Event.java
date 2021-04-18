package org.kevin;

import lombok.RequiredArgsConstructor;

/**
 * 事件枚举
 */
@RequiredArgsConstructor
public enum Event {

    STARK_SIGHTED("Stark sighted"),
    WARSHIPS_APPROACHING("Warships approaching"),
    TRAITOR_DETECTED("Traitor detected");

    private final String description;

    @Override
    public String toString() {
        return description;
    }
}
