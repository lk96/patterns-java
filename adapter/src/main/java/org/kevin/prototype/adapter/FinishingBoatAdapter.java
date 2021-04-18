package org.kevin.prototype.adapter;

public class FinishingBoatAdapter implements RowingBoat{

    private final FinishingBoat finishingBoat;

    public FinishingBoatAdapter() {
        finishingBoat = new FinishingBoat();
    }

    @Override
    public void row() {
        finishingBoat.sail();
    }
}
