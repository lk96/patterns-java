package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorView implements View{
    @Override
    public void display() {
      log.error("Error 500");
    }
}
