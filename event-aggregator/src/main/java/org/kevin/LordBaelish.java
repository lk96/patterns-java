package org.kevin;

public class LordBaelish extends EventEmitter{

    public LordBaelish() {
    }

    public LordBaelish(EventObserver observers) {
        super(observers);
    }

    @Override
    public void timePasses(Weekday weekday) {
        if (weekday == Weekday.FRIDAY) {
            notifyObservers(Event.STARK_SIGHTED);
        }
    }
}
