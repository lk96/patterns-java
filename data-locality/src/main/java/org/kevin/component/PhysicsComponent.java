package org.kevin.component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhysicsComponent implements Component{

    @Override
    public void update() {
        log.info("Update physics component of game");
    }

    @Override
    public void render() {

    }
}
