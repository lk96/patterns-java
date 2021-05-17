package org.kevin;

public class VariableStepGameLoop extends GameLoop {
    @Override
    protected void processGameLoop() {
        long lastFrameTime = System.currentTimeMillis();
        while (isGameRunning()) {
            processInput();
            long currentFrameTime = System.currentTimeMillis();
            long elapsedTime = currentFrameTime - lastFrameTime;
            update(elapsedTime);
            lastFrameTime = currentFrameTime;
            render();
        }
    }

    public void update(Long elapsedTime) {
        controller.moveBullet(0.5f * elapsedTime / 100);
    }
}
