package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public abstract class DwarvenMineWorker {

    public void goToSleep() {
        log.info("{} goes to sleep", name());
    }

    public void wakeup() {
        log.info("{} wakes up.", name());
    }

    public void goHome() {
        log.info("{} goes home.", name());
    }

    public void goToMine() {
        log.info("{} goes to mine.", name());
    }

    private void action(Action action) {
        switch (action) {
            case WORK:
                work();
                break;
            case GO_TO_SLEEP:
                goToSleep();
                break;
            case GO_HOME:
                goHome();
                break;
            case WAKE_UP:
                wakeup();
                break;
            case GO_TO_MINE:
                goToMine();
                break;
            default:
                log.info("undefined action");
                break;
        }
    }


    public void action(Action... actions) {
        Arrays.stream(actions).forEach(this::action);
    }

    public abstract void work();

    public abstract String name();

    enum Action {
        GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
    }
}
