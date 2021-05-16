package org.kevin;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BallThread extends Thread {

    @Setter
    private BallItem twin;

    private volatile boolean isSuspended;

    private volatile boolean isRunning = true;

    public void suspendMe() {
        isSuspended = true;
        log.info("Begin to suspended BallThread");
    }

    public void resumeMe() {
        isSuspended = false;
        log.info("Begin to resume BallThread");
    }

    public void stopMe() {
        this.isSuspended = true;
        this.isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            if (!isSuspended) {
                twin.draw();
                twin.move();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}
