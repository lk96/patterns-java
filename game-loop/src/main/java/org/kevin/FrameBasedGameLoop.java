package org.kevin;

public class FrameBasedGameLoop extends GameLoop {
    @Override
    protected void processGameLoop() {
        while (isGameRunning()) {
            processInput();
            update();
            render();
        }
    }

    public void update() {
        controller.moveBullet(0.5f);
    }

}
