package org.kevin;

import java.util.LinkedList;
import java.util.List;

/**
 * 可以观察到的事件生成器的基类
 */
public abstract class EventEmitter {

    private final List<EventObserver> observers;

    public EventEmitter() {
        this.observers = new LinkedList<>();
    }

    public EventEmitter(EventObserver observers) {
        this();
        registerObserver(observers);
    }

    public final void registerObserver(EventObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Event event) {
        observers.forEach(observer -> observer.onEvent(event));
    }

    public abstract void timePasses(Weekday weekday);
}
