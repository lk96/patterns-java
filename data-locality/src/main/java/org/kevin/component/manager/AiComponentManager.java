package org.kevin.component.manager;

import lombok.extern.slf4j.Slf4j;
import org.kevin.component.AiComponent;
import org.kevin.component.Component;

import java.util.stream.IntStream;

@Slf4j
public class AiComponentManager {

    private static final int MAX_ENTITIES = 10000;

    private final int numEntities;

    private final Component[] aiComponents = new AiComponent[MAX_ENTITIES];

    public AiComponentManager(int numEntities) {
        this.numEntities = numEntities;
    }

    public void start() {
        log.info("Start Ai Game Component");
        IntStream.range(0, numEntities).forEach(i -> aiComponents[i] = new AiComponent());
    }

    public void update() {
        log.info("Update Ai Game Component");
        IntStream.range(0, numEntities)
                .filter(i -> aiComponents.length > i && aiComponents[i] != null)
                .forEach(i -> aiComponents[i].update());
    }
}
