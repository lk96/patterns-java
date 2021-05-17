package org.kevin;

public class FixedStepGameLoop extends GameLoop{

    private static final long MS_PER_FRAME = 20;

    @Override
    protected void processGameLoop() {
        long previousTime = System.currentTimeMillis();
        long lag = 0l;
        while (isGameRunning()) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - previousTime;
            previousTime = currentTime;
            lag += elapsedTime;
            processInput();
            while (lag >= MS_PER_FRAME) {
                update();
                lag -= MS_PER_FRAME;
            }
            render();
        }
    }

    protected void update(){
        controller.moveBullet(0.5f * MS_PER_FRAME / 100);
    }
}
