package org.kevin;

public class UnknownCommand implements Command{
    @Override
    public void process() {
        new ErrorView().display();
    }
}
