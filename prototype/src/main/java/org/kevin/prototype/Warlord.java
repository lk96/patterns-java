package org.kevin.prototype;

public abstract class Warlord implements Prototype {

    @Override
    public abstract Warlord copy();

    public Warlord() {

    }

    public Warlord(Warlord warlord) {

    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }
}
