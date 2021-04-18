package org.kevin;

import lombok.extern.slf4j.Slf4j;

/**
 * 士兵访问者
 */
@Slf4j
public class SoldierVisitor implements UnitVisitor {

    @Override
    public void visitSoldier(Soldier soldier) {
        log.info("Greetings {}", soldier);
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {

    }

    @Override
    public void visitCommander(Commander commander) {

    }
}
