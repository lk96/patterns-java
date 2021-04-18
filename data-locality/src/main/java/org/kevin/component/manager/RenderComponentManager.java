package org.kevin.component.manager;

import lombok.extern.slf4j.Slf4j;
import org.kevin.component.Component;
import org.kevin.component.RenderComponent;

import java.util.stream.IntStream;

@Slf4j
public class RenderComponentManager {

    private static final int MAX_ENTITIES = 10000;

    private final int numEntities;

    private final Component[] renderComponent = new RenderComponent[MAX_ENTITIES];

    public RenderComponentManager(int numEntities) {
        this.numEntities = numEntities;
    }

    public void start() {
        log.info("Start Render Game Component");
        IntStream.range(0, numEntities).forEach(i -> renderComponent[i] = new RenderComponent());
    }

    public void update() {
        log.info("Update Render Game Component");
        IntStream.range(0, numEntities)
                .filter(i -> renderComponent.length > i && renderComponent[i] != null)
                .forEach(i -> renderComponent[i].render());
    }
}
