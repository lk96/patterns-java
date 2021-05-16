package org.kevin;

public class ArcherCommand implements Command{
    @Override
    public void process() {
        new ArcherView().display();
    }
}
