package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DwarvenGoldDigger extends DwarvenMineWorker{
    @Override
    public void work() {
      log.info("{} digs for gold",name());
    }

    @Override
    public String name() {
        return "Dwarf gold digger";
    }
}
