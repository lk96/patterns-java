package org.kevin.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Hero mage = new Hero.Builder(Profession.MAGE, "Riobard")
                .withHariColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER)
                .build();
        log.info(mage.toString());

        Hero thief = new Hero.Builder(Profession.THIEF, "Desmond")
                .withHairType(HairType.BALD)
                .withWeapon(Weapon.BOW)
                .build();
        log.info(thief.toString());

        Hero warrior = new Hero.Builder(Profession.WARRIOR, "Amber")
                .withHariColor(HairColor.BLOND)
                .withHairType(HairType.LONG_CURLY)
                .withArmor(Armor.CHAIN_MAIL)
                .withWeapon(Weapon.SWORD)
                .build();
        log.info(warrior.toString());
    }
}
