package org.kevin.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hammer implements Weapon{

    private static final Logger log = LoggerFactory.getLogger(Hammer.class);

    private final Enchantment enchantment;

    public Hammer(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        log.info("The hammer is wielded");
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        log.info("The hammer is swing");
        enchantment.apply();
    }

    @Override
    public void unWield() {
        log.info("The hammer is unwWeld");
        enchantment.onDeactivate();
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}
