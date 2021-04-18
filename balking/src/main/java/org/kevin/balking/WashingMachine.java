package org.kevin.balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class WashingMachine {

    private static final Logger log = LoggerFactory.getLogger(WashingMachine.class);

    private final DelayProvider delayProvider;

    private WashingMachineState washingMachineState;

    public WashingMachine() {
        this((interval, timeUnit, task) -> {
            try {
                Thread.sleep(timeUnit.toMillis(interval));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.run();
        });
    }

    public WashingMachine(DelayProvider delayProvider) {
        this.delayProvider = delayProvider;
        this.washingMachineState = WashingMachineState.ENABLED;
    }

    public WashingMachineState getWashingMachineState() {
        return washingMachineState;
    }

    public void wash(){
        synchronized (this) {
            WashingMachineState washingMachineState = getWashingMachineState();
            log.info("{}:Actual machine state:{}",Thread.currentThread().getName(),washingMachineState);
            if (washingMachineState ==  WashingMachineState.WASHING) {
                log.info("ERROR: Cannot wash if the machine has benn already washing");
                return;
            }
            this.washingMachineState = WashingMachineState.WASHING;
        }
        log.info("{}:Doing the washing",Thread.currentThread().getName());
        this.delayProvider.executeAfter(50, TimeUnit.SECONDS,this::endOfWashing);
    }

    public synchronized void endOfWashing(){
        washingMachineState = WashingMachineState.ENABLED;
        log.info("{}:Washing completed", Thread.currentThread().getId());
    }
}
