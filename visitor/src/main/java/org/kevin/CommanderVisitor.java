package org.kevin;

import lombok.extern.slf4j.Slf4j;

/**
 * 指挥官访问者
 */
@Slf4j
public class CommanderVisitor implements UnitVisitor{
    @Override
    public void visitSoldier(Soldier soldier) {

    }

    @Override
    public void visitSergeant(Sergeant sergeant) {

    }

    @Override
    public void visitCommander(Commander commander) {
        log.info("Good to see you {}", commander);
    }
}
