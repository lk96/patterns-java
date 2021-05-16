package org.kevin;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class OrcBlacksmith implements Blacksmith{

    private static final Map<WeaponType, OrcWeapon> ORCARSENAL;

    static {
        ORCARSENAL = new EnumMap<>(WeaponType.class);
        Arrays.stream(WeaponType.values()).forEach(weaponType -> ORCARSENAL.put(weaponType,new OrcWeapon(weaponType)));
    }
    @Override
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return ORCARSENAL.get(weaponType);
    }
}
