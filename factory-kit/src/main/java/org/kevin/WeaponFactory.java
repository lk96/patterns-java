package org.kevin;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface WeaponFactory {

    Weapon create(WeaponType type);

    static WeaponFactory factory(Consumer<Builder> consumer) {
        HashMap<WeaponType, Supplier<Weapon>> map = new HashMap<>();
        consumer.accept(map::put);
        return name -> map.get(name).get();
    }
}
