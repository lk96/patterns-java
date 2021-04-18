package org.kevin.prototype.abstractfactory;

public class OrcKing implements King {

    static final String DESCRIPTION = "This is the Orc King";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
