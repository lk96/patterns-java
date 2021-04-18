package org.kevin;

public class Scout extends EventEmitter {

    public Scout() {
    }

    public Scout(EventObserver observers) {
        super(observers);
    }

    @Override
    public void timePasses(Weekday weekday) {
        if (weekday == Weekday.WEDNESDAY) {
            notifyObservers(Event.WARSHIPS_APPROACHING);
        }
    }
}
