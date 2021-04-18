package org.kevin.prototype;

public class ElfWarlord extends Warlord {

    private final String helpType;

    public ElfWarlord(String helpType) {
        this.helpType = helpType;
    }

    public ElfWarlord(ElfWarlord warlord) {
        super(warlord);
        this.helpType = warlord.helpType;
    }

    @Override
    public ElfWarlord copy() {
        return new ElfWarlord(this);
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
        var other = (ElfWarlord) obj;
        if (helpType == null) {
            return other.helpType == null;
        }
        return helpType.equals(other.helpType);
    }

    @Override
    public String toString() {
        return "Elven mage helps in " + helpType;
    }
}
