package org.kevin.prototype.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private final Kingdom kingdom = new Kingdom();

    public Kingdom getKingdom(){
        return kingdom;
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    @Override
    public void run() {
        LOGGER.info("Elf Kingdom");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
        LOGGER.info(kingdom.getArmy().getDescription());
        LOGGER.info(kingdom.getKing().getDescription());
        LOGGER.info(kingdom.getCastle().getDescription());

        LOGGER.info("Orc Kingdom");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
        LOGGER.info(kingdom.getArmy().getDescription());
        LOGGER.info(kingdom.getKing().getDescription());
        LOGGER.info(kingdom.getCastle().getDescription());
    }

    private void createKingdom(final Kingdom.FactoryMaker.KingdomType type) {
        final KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(type);
        kingdom.setKing(kingdomFactory.createKing());
        kingdom.setArmy(kingdomFactory.createArmy());
        kingdom.setCastle(kingdomFactory.createCastle());
    }
}
