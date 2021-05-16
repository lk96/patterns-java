package org.kevin;

public class CatapultCommand implements Command{
    @Override
    public void process() {
        new CatapultView().display();
    }
}
