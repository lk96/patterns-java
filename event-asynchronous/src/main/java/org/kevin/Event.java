package org.kevin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Event implements IEvent, Runnable {

    private final int eventId;

    private final int eventTime;

    @Getter
    private final boolean synchronous;

    private Thread thread;

    private boolean isComplete = false;

    private ThreadCompleteListener listener;

    @Override
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        long endTime = currentTimeMillis + (eventTime * 1000);
        while (System.currentTimeMillis() < endTime) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        isComplete = true;
        completed();
    }

    @Override
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop() {
        if (thread == null) {
            return;
        }
        thread.interrupt();
    }

    @Override
    public void status() {
        if (!isComplete) {
            log.info("[{}] is not done.", eventId);
        } else {
            log.info("[{}] is done.", eventId);
        }
    }

    public final void addListener(final ThreadCompleteListener listener) {
        this.listener = listener;
    }

    public final void removeListener(final ThreadCompleteListener listener) {
        this.listener = null;
    }

    private void completed() {
        if (listener != null) {
            listener.completedEventHandler(eventId);
        }
    }
}
