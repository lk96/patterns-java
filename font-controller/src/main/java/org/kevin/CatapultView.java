package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatapultView implements View{
    @Override
    public void display() {
      log.info("Displaying catapult");
    }
}
