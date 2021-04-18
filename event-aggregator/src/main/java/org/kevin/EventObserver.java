package org.kevin;

/**
 * 事件的观察者接口
 */
public interface EventObserver {

    void onEvent(Event event);
}
