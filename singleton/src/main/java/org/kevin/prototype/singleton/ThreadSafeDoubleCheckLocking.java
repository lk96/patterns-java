package org.kevin.prototype.singleton;

public class ThreadSafeDoubleCheckLocking {

    private static volatile ThreadSafeDoubleCheckLocking instance;

    private static boolean flag = true;

    private ThreadSafeDoubleCheckLocking() {
        if (flag) {
            flag = false;
        } else {
            throw new IllegalStateException("already init");
        }
    }

    public static ThreadSafeDoubleCheckLocking getInstance() {
        var result = instance;

        if (result == null) {
            synchronized (ThreadSafeDoubleCheckLocking.class) {
                result = instance;
                if (result == null) {
                    instance = result = new ThreadSafeDoubleCheckLocking();
                }
            }
        }
        return result;
    }
}
