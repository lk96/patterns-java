package org.kevin;

import java.util.function.Supplier;

public interface Builder {

    void add(WeaponType name, Supplier<Weapon> supplier);
}
