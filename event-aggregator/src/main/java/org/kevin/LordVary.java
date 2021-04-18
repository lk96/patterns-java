package org.kevin;

public class LordVary extends EventEmitter{

    public LordVary() {
    }

    public LordVary(EventObserver observers) {
        super(observers);
    }

    @Override
    public void timePasses(Weekday weekday) {
        if (weekday == Weekday.SATURDAY) {
            notifyObservers(Event.TRAITOR_DETECTED);
        }
    }
}
