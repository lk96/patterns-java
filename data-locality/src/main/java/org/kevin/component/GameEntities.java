package org.kevin.component;

import lombok.extern.slf4j.Slf4j;
import org.kevin.component.manager.AiComponentManager;
import org.kevin.component.manager.PhysicsComponentManager;
import org.kevin.component.manager.RenderComponentManager;

@Slf4j
public class GameEntities {

    private final AiComponentManager aiComponentManager;

    private final PhysicsComponentManager physicsComponentManager;

    private final RenderComponentManager renderComponentManager;

    public GameEntities(int numEntities) {
        log.info("init game with entities:{}", numEntities);
        aiComponentManager = new AiComponentManager(numEntities);
        physicsComponentManager = new PhysicsComponentManager(numEntities);
        renderComponentManager = new RenderComponentManager(numEntities);
    }

    public void start(){
        log.info("start game");
        aiComponentManager.start();
        physicsComponentManager.start();
        renderComponentManager.start();
    }

    public void update(){
        log.info("update game component");
        aiComponentManager.update();
        physicsComponentManager.update();
        renderComponentManager.update();
    }
}
