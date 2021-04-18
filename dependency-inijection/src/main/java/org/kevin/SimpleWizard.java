package org.kevin;

public class SimpleWizard implements Wizard{

    private final OldTobyTobacco tobacco = new OldTobyTobacco();

    @Override
    public void smoke() {
        tobacco.smoke(this);
    }
}
