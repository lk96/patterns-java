package org.kevin;

import java.util.EnumMap;
import java.util.Map;

public class PotionFactory {

    private final Map<PotionType, Potion> potionMap;

    public PotionFactory() {
        this.potionMap = new EnumMap<>(PotionType.class);
    }

    Potion createPotion(PotionType type) {
        Potion potion = potionMap.get(type);
        if (potion == null) {
            switch (type) {
                case HEALING :
                    potion = new HealingPotion();
                    potionMap.put(type, potion);
                    break;
                case HOLY_WATER:
                    potion = new HolyWaterPotion();
                    potionMap.put(type, potion);
                    break;
                case INVISIBILITY:
                    potion = new InvisibilityPotion();
                    potionMap.put(type, potion);
                    break;
                case POISON:
                    potion = new PoisonPotion();
                    potionMap.put(type, potion);
                    break;
                case STRENGTH:
                    potion = new StrengthPotion();
                    potionMap.put(type, potion);
                    break;
                default:
                    break;
            }
        }
        return potion;
    }
}
