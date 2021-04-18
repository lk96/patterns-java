package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class App {

    public static void main(String[] args) {
        Inventory inventory = new Inventory(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        IntStream.range(0,3).<Runnable>mapToObj(i->()->{
            while (inventory.addItem(new Item())){
                log.info("Adding another item");
            }
        }).forEach(executorService::execute);
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }catch (InterruptedException e) {
            log.info("Error waiting for ExecutorService shutdown");
            Thread.currentThread().interrupt();

        }
    }
}
