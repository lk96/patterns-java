package org.kevin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrcWeapon implements Weapon{

    private final WeaponType weaponType;

    @Override
    public String toString() {
        return "Orcish " + weaponType;
    }
}
