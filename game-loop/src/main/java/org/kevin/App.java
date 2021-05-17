package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    private static final int GAME_LOOP_DURATION_TIME = 2000;

    public static void main(String[] args) {

        try {
            log.info("Start frame-based game loop:");
            var frameBasedGameLoop = new FrameBasedGameLoop();
            frameBasedGameLoop.run();
            Thread.sleep(GAME_LOOP_DURATION_TIME);
            frameBasedGameLoop.stop();
            log.info("Stop frame-based game loop.");

            log.info("Start variable-step game loop:");
            var variableStepGameLoop = new VariableStepGameLoop();
            variableStepGameLoop.run();
            Thread.sleep(GAME_LOOP_DURATION_TIME);
            variableStepGameLoop.stop();
            log.info("Stop variable-step game loop.");

            log.info("Start fixed-step game loop:");
            var fixedStepGameLoop = new FixedStepGameLoop();
            fixedStepGameLoop.run();
            Thread.sleep(GAME_LOOP_DURATION_TIME);
            fixedStepGameLoop.stop();
            log.info("Stop variable-step game loop.");

        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
