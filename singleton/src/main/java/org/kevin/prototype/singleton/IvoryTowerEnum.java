package org.kevin.prototype.singleton;

public enum IvoryTowerEnum {

    INSTANCE;

    @Override
    public String toString() {
        return getDeclaringClass().getCanonicalName() + "@" + hashCode();
    }
}
