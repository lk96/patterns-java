package org.kevin.prototype.singleton;

public class ThreadSafeLazyLoadIvoryTower {

    private static volatile ThreadSafeLazyLoadIvoryTower instance;

    private ThreadSafeLazyLoadIvoryTower() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("already init");
        }
    }

    public static synchronized ThreadSafeLazyLoadIvoryTower getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeLazyLoadIvoryTower.class) {
                if (instance == null) {
                    instance = new ThreadSafeLazyLoadIvoryTower();
                }
            }
        }
        return instance;
    }
}
