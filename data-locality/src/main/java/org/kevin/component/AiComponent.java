package org.kevin.component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AiComponent implements Component{

    @Override
    public void update() {
        log.info("update Ai component");
    }

    @Override
    public void render() {

    }
}
