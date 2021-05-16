package org.kevin;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class ElfBlacksmith implements Blacksmith{

    private static final Map<WeaponType,ElfWeapon> ELFARSENAL;

    static {
        ELFARSENAL = new EnumMap<>(WeaponType.class);
        Arrays.stream(WeaponType.values()).forEach(weaponType -> ELFARSENAL.put(weaponType,new ElfWeapon(weaponType)));
    }
    @Override
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return ELFARSENAL.get(weaponType);
    }
}
