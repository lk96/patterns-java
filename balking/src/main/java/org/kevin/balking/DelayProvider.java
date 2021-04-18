package org.kevin.balking;

import java.util.concurrent.TimeUnit;

public interface DelayProvider {

    void executeAfter(long interval, TimeUnit timeUnit, Runnable task);
}
