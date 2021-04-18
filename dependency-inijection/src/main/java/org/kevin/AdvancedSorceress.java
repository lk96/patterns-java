package org.kevin;

import lombok.Setter;

@Setter
public class AdvancedSorceress implements Wizard{

    private Tobacco tobacco;

    @Override
    public void smoke() {
        tobacco.smoke(this);
    }
}
