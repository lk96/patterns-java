package org.kevin.prototype;

public class ElfBeast extends Beast{

    private final String helpType;

    public ElfBeast(String helpType) {
        this.helpType = helpType;
    }

    public ElfBeast(ElfBeast beast) {
        super(beast);
        this.helpType = beast.helpType;
    }

    @Override
    public ElfBeast copy() {
        return new ElfBeast(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        var other = (ElfBeast) obj;
        if (helpType == null) {
            return other.helpType == null;
        }
        return helpType.equals(other.helpType);
    }

    @Override
    public String toString() {
        return "Elven beast helps in " + helpType;
    }
}
