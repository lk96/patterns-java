package org.kevin.prototype.singleton;

public class IvoryTower {

    private IvoryTower() {
    }

    private static final IvoryTower INSTANCE = new IvoryTower();

    public static IvoryTower getInstance() {
        return INSTANCE;
    }
}
