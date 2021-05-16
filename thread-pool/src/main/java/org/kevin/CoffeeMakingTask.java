package org.kevin;

public class CoffeeMakingTask extends Task {

    private static final int TIME_PER_CPU=100;

    public CoffeeMakingTask(int numCups) {
        super(numCups * TIME_PER_CPU);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getSimpleName(), super.toString());
    }
}
