package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class App {


    public static void main(String[] args) {
        List<Car> cars = CarFactory.createCars();
        
        List<String> modelsImperative = ImperativeProgramming.getModelsAfter2000(cars);
        log.info(modelsImperative.toString());

        List<String> modelsFunctional = FunctionalProgramming.getModelsAfter2000(cars);
        log.info(modelsFunctional.toString());

        Map<Category, List<Car>> groupingOfCarByCategoryImperative = ImperativeProgramming.getGroupingOfCarByCategory(cars);
        log.info(groupingOfCarByCategoryImperative.toString());

        Map<Category, List<Car>> groupingOfCarsByCategoryFunctional = FunctionalProgramming.getGroupingOfCarsByCategory(cars);
        log.info(groupingOfCarsByCategoryFunctional.toString());

        Person person = new Person(cars);

        List<Car> sedanCarsOwnedImperative = ImperativeProgramming.getSedanCarsOwnedSortedByDate(List.of(person));
        log.info(sedanCarsOwnedImperative.toString());

        List<Car> sedanCarsOwnedFunctional = FunctionalProgramming.getSedanCarsOwnedSortedByDate(List.of(person));
        log.info(sedanCarsOwnedFunctional.toString());
    }
}
