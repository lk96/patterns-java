package org.kevin;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BallItem extends GameItem {

    private boolean isSuspended;

    @Setter
    private BallThread twin;

    @Override
    public void doDraw() {
        log.info("doDraw");
    }

    public void move() {
        log.info("move");
    }

    @Override
    public void click() {
        isSuspended = !isSuspended;
        if (isSuspended) {
            twin.suspendMe();
        } else {
            twin.resumeMe();
        }
    }
}
