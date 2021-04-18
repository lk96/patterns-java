package org.kevin.prototype.abstractfactory;

public class ElfArmy implements Army{

    static final String DESCRIPTION = "This is the Elven Army";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}