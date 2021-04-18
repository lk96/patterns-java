package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class StealingMethod {

    protected abstract String pickTarget();

    protected abstract void confuseTarget(String target);

    protected abstract void stealTheItem(String target);

    public void steal() {
        String target = pickTarget();
        log.info("The target has benn choose as {}",target);
        confuseTarget(target);
        stealTheItem(target);
    }
}
