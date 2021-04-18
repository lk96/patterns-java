package org.kevin;

/**
 * KingsHand观察来自多个源的事件并发送给监听器
 */
public class KingsHand extends EventEmitter implements EventObserver{
    public KingsHand() {
    }

    public KingsHand(EventObserver observers) {
        super(observers);
    }

    @Override
    public void timePasses(Weekday weekday) {

    }

    @Override
    public void onEvent(Event event) {
        notifyObservers(event);
    }
}
