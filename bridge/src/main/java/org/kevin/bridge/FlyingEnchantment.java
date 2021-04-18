package org.kevin.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlyingEnchantment implements Enchantment{

    private static final Logger log = LoggerFactory.getLogger(FlyingEnchantment.class);

    @Override
    public void onActivate() {
        log.info("The item begins to glow faintly.");
    }

    @Override
    public void apply() {
        log.info("The item flies and strikes the enemies finally returning to owner's hand.");
    }

    @Override
    public void onDeactivate() {
        log.info("The item's glow fades");
    }
}
