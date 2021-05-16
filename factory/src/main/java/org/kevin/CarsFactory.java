package org.kevin;

public class CarsFactory {

    public static Car getCar(CarType type) {
        return type.getConstructor().get();
    }
}
