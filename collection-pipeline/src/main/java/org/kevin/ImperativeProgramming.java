package org.kevin;

import java.util.*;
import java.util.stream.Collectors;

public class ImperativeProgramming {

    private ImperativeProgramming() {
    }

    public static List<String> getModelsAfter2000(List<Car> cars) {
        ArrayList<Car> sortedCar = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYear() > 2000) {
                sortedCar.add(car);
            }
        }
        Collections.sort(sortedCar, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getYear()-o2.getYear();
            }
        });
        ArrayList<String> models = new ArrayList<>();
        for (Car car : sortedCar) {
            models.add(car.getModel());
        }
        return models;
    }

    public static Map<Category, List<Car>> getGroupingOfCarByCategory(List<Car> cars) {
        HashMap<Category, List<Car>> categoryListHashMap = new HashMap<>();
        for (Car car : cars) {
            if (categoryListHashMap.containsKey(car.getCategory())) {
                categoryListHashMap.get(car.getCategory()).add(car);
            }else {
                ArrayList<Car> list = new ArrayList<>();
                list.add(car);
                categoryListHashMap.put(car.getCategory(), list);
            }
        }
        return categoryListHashMap;
    }

    public static List<Car> getSedanCarsOwnedSortedByDate(List<Person> personList) {
        ArrayList<Car> cars = new ArrayList<>();
        for (Person person : personList) {
            cars.addAll(person.getCars());
        }
        ArrayList<Car> sedanCars = new ArrayList<>();
        for (Car car : cars) {
            if (Category.SEDAN.equals(car.getCategory())) {
                sedanCars.add(car);
            }
        }
        sedanCars.sort(new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getYear()-o2.getYear();
            }
        });
        return sedanCars;
    }
}
