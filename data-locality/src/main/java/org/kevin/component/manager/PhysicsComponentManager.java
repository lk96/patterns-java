package org.kevin.component.manager;

import lombok.extern.slf4j.Slf4j;
import org.kevin.component.Component;
import org.kevin.component.PhysicsComponent;

import java.util.stream.IntStream;

@Slf4j
public class PhysicsComponentManager {

    private static final int MAX_ENTITIES = 10000;

    private final int numEntities;

    private final Component[] physicsComponent = new Component[MAX_ENTITIES];

    public PhysicsComponentManager(int numEntities) {
        this.numEntities = numEntities;
    }

    public void start() {
        log.info("Start Physics Game Component");
        IntStream.range(0, numEntities).forEach(i -> physicsComponent[i] = new PhysicsComponent());
    }

    public void update() {
        log.info("Update Physics Game Component");
        IntStream.range(0, numEntities)
                .filter(i -> physicsComponent.length > i && physicsComponent[i] != null)
                .forEach(i -> physicsComponent[i].update());
    }
}
