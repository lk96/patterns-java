package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;

@Slf4j
public abstract class GameLoop {

    protected volatile GameStatus status;

    protected final GameController controller;

    private Thread gameThread;

    public GameLoop() {
        controller = new GameController();
        status = GameStatus.STOPPED;
    }

    public void run() {
        status = GameStatus.RUNNING;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    public void stop() {
        status = GameStatus.STOPPED;
    }

    public boolean isGameRunning() {
        return status == GameStatus.RUNNING;
    }

    protected void processInput() {
        try {
            int lag = new SecureRandom().nextInt(200) + 50;
            Thread.sleep(lag);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    protected void render() {
        float position = controller.getBulletPosition();
        log.info("Current bullet position:{}", position);
    }

    protected abstract void processGameLoop();
}
