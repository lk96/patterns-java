package org.kevin;

import lombok.extern.slf4j.Slf4j;

/**
 * 中士访问者
 */
@Slf4j
public class SergeantVisitor implements UnitVisitor{
    @Override
    public void visitSoldier(Soldier soldier) {

    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        log.info("Hello {}", sergeant);
    }

    @Override
    public void visitCommander(Commander commander) {

    }
}
