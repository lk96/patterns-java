package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("start...");

        var tasks = List.of(
                new PotatoPeelingTask(3),
                new PotatoPeelingTask(6),
                new CoffeeMakingTask(2),
                new CoffeeMakingTask(6),
                new PotatoPeelingTask(4),
                new CoffeeMakingTask(2),
                new PotatoPeelingTask(4),
                new CoffeeMakingTask(9),
                new PotatoPeelingTask(3),
                new CoffeeMakingTask(2),
                new PotatoPeelingTask(4),
                new CoffeeMakingTask(2),
                new CoffeeMakingTask(7),
                new PotatoPeelingTask(4),
                new PotatoPeelingTask(5));

        ExecutorService executor = Executors.newFixedThreadPool(3);
        tasks.stream().map(Worker::new).forEach(executor::execute);
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.yield();
        }
        log.info("end..");
    }
}
