package org.kevin.component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RenderComponent implements Component{
    @Override
    public void update() {

    }

    @Override
    public void render() {
      log.info("Render component");
    }
}
