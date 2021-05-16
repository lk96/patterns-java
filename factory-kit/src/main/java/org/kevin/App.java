package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {
        WeaponFactory factory = WeaponFactory.factory(builder -> {
            builder.add(WeaponType.SWORD, Sword::new);
            builder.add(WeaponType.AXE, Axe::new);
            builder.add(WeaponType.BOW, Bow::new);
            builder.add(WeaponType.SPEAR, Spear::new);
        });
        Weapon weapon = factory.create(WeaponType.AXE);
        log.info(weapon.toString());
    }
}
