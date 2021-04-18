package org.kevin.prototype;

public class OrcMage extends Mage{

    private final String weapon;

    public OrcMage(String weapon) {
        this.weapon = weapon;
    }

    public OrcMage(OrcMage mage) {
        super(mage);
        this.weapon = mage.weapon;
    }

    @Override
    public Mage copy() {
        return new OrcMage(this);
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
        var other = (OrcMage) obj;
        if (weapon == null) {
            return other.weapon == null;
        }
        return weapon.equals(other.weapon);
    }
}
