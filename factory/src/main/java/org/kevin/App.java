package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {
        Car ford = CarsFactory.getCar(CarType.FORD);
        Car ferrari = CarsFactory.getCar(CarType.FERRARI);
        log.info(ford.getDescription());
        log.info(ferrari.getDescription());
    }
}
