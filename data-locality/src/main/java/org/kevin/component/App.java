package org.kevin.component;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    private static final int NUM_ENTITIES = 5;

    public static void main(String[] args) {
        log.info("start game app using data-locality pattern");
        GameEntities gameEntities = new GameEntities(NUM_ENTITIES);
        gameEntities.start();
        gameEntities.update();
    }
}
