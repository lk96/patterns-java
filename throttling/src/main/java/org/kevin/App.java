package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class App {

    public static void main(String[] args) {
        var callsCount = new CallsCount();
        var adidas = new Tenant("Adidas", 5, callsCount);
        var nike = new Tenant("Nike", 6, callsCount);

        var executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> makeServiceCalls(adidas, callsCount));
        executorService.execute(() -> makeServiceCalls(nike, callsCount));

        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Executor Service terminated: {}", e.getMessage());
        }
    }

    private static void makeServiceCalls(Tenant tenant, CallsCount callsCount) {
        var timer = new ThrottleTimerImpl(10, callsCount);
        var service = new B2BService(timer, callsCount);
        // Sleep is introduced to keep the output in check and easy to view and analyze the results.
        IntStream.range(0, 20).forEach(i -> {
            service.dummyCustomerApi(tenant);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.error("Thread interrupted: {}", e.getMessage());
            }
        });
    }
}
