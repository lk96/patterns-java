package org.kevin.prototype.abstractfactory;

public class ElfKing implements King {

    static final String DESCRIPTION = "This is the Elven king";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
