package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Worker implements Runnable{

    private final Task task;

    public Worker(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        log.info("{} processing {}", Thread.currentThread().getName(), task.toString());
        try {
            Thread.sleep(task.getTimeMs());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
