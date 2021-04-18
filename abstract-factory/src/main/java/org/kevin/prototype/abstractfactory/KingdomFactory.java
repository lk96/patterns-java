package org.kevin.prototype.abstractfactory;

public interface KingdomFactory {

    Castle createCastle();

    King createKing();

    Army createArmy();
}
