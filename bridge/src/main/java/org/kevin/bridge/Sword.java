package org.kevin.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sword implements Weapon {

    private static final Logger log = LoggerFactory.getLogger(Sword.class);

    private final Enchantment enchantment;

    public Sword(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        log.info("The Sword is wielded.");
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        log.info("The sword is swing");
        enchantment.apply();
    }

    @Override
    public void unWield() {
        log.info("The Sword is unWield");
        enchantment.onDeactivate();
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}
