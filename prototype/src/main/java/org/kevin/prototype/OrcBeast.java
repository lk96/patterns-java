package org.kevin.prototype;

public class OrcBeast extends Beast {

    private final String weapon;

    public OrcBeast(String weapon) {
        this.weapon = weapon;
    }

    public OrcBeast(OrcBeast beast) {
        super(beast);
        this.weapon = beast.weapon;
    }

    @Override
    public OrcBeast copy() {
        return new OrcBeast(this);
    }

    @Override
    public String toString() {
        return "OrcBeast wolf attacks with" + weapon;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        var other = (OrcBeast) obj;
        if (weapon == null) {
            return other.weapon == null;
        }
        return weapon.equals(other.weapon);
    }
}
