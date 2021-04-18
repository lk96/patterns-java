package org.kevin.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        HeroFactoryImpl factory = new HeroFactoryImpl(
                new ElfMage("cooking"),
                new ElfWarlord("cleaning"),
                new ElfBeast("protecting")
        );

        Mage mage = factory.createMage();
        Warlord warlord = factory.createWarlord();
        Beast beast = factory.createBeast();
        log.info(mage.toString());
        log.info(warlord.toString());
        log.info(beast.toString());

        factory = new HeroFactoryImpl(
                new OrcMage("axe"),
                new OrcWarlord("sword"),
                new OrcBeast("laser")
        );
        mage = factory.createMage();
        warlord = factory.createWarlord();
        beast = factory.createBeast();
        log.info(mage.toString());
        log.info(warlord.toString());
        log.info(beast.toString());
    }
}
