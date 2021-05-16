package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AlchemistShop {

    private final List<Potion> topShelf;

    private final List<Potion> bottomShelf;

    public AlchemistShop() {
        PotionFactory factory = new PotionFactory();
        topShelf = List.of(
                factory.createPotion(PotionType.INVISIBILITY),
                factory.createPotion(PotionType.STRENGTH),
                factory.createPotion(PotionType.STRENGTH),
                factory.createPotion(PotionType.HEALING),
                factory.createPotion(PotionType.INVISIBILITY),
                factory.createPotion(PotionType.STRENGTH),
                factory.createPotion(PotionType.HEALING),
                factory.createPotion(PotionType.HEALING)
        );
        bottomShelf = List.of(
                factory.createPotion(PotionType.HOLY_WATER),
                factory.createPotion(PotionType.HOLY_WATER),
                factory.createPotion(PotionType.POISON),
                factory.createPotion(PotionType.POISON),
                factory.createPotion(PotionType.POISON)
        );
    }

    public final List<Potion> getTopShelf(){
        return List.copyOf(this.topShelf);
    }

    public final List<Potion> getBottomShelf(){
        return List.copyOf(this.bottomShelf);
    }

    public void enumerate(){
        log.info("Enumerating top shelf potions\n");
        topShelf.forEach(Potion::drink);
        log.info("Enumerating bottom shelf potions\n");
        bottomShelf.forEach(Potion::drink);
    }
}
