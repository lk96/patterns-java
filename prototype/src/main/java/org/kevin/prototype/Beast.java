package org.kevin.prototype;

public abstract class Beast implements Prototype{

    public Beast() {
    }

    public Beast(Beast beast) {
    }

    @Override
    public abstract Beast copy();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }
}
