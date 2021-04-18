package org.kevin.prototype;

public class OrcWarlord extends Warlord{

    private final String weapon;

    public OrcWarlord(String weapon) {
        this.weapon = weapon;
    }

    public OrcWarlord(OrcWarlord warlord) {
        super(warlord);
        this.weapon = warlord.weapon;
    }

    @Override
    public Warlord copy() {
        return new OrcWarlord(this);
    }

    @Override
    public String toString() {
        return "OrcWarlord wolf attacks with" + weapon;
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
        var other = (OrcWarlord) obj;
        if (weapon == null) {
            return other.weapon == null;
        }
        return weapon.equals(other.weapon);
    }
}
