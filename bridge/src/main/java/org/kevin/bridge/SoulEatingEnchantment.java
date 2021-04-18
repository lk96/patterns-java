package org.kevin.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoulEatingEnchantment implements Enchantment{

    private static final Logger log = LoggerFactory.getLogger(SoulEatingEnchantment.class);

    @Override
    public void onActivate() {
        log.info("The item spreads bloodlust");
    }

    @Override
    public void apply() {
        log.info("The item eats the soul of enemies");
    }

    @Override
    public void onDeactivate() {
        log.info("Bloodlust slowly disappears");
    }
}
