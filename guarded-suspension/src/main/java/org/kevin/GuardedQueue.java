package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class GuardedQueue {

    private final Queue<Integer> sourceList;

    public GuardedQueue() {
        this.sourceList = new LinkedList<>();
    }

    public synchronized Integer get(){
        while (sourceList.isEmpty()) {
            try {
                log.info("waiting...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("getting...");
        return sourceList.peek();
    }

    public synchronized void put(Integer e) {
        log.info("putting...");
        sourceList.add(e);
        log.info("notifying...");
        notify();
    }
}
