package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {

        long serverStartTime = System.nanoTime();

        DelayedRemoteService delayedRemoteService = new DelayedRemoteService(serverStartTime, 5);
        DefaultCircuitBreaker delayServiceCircuitBreaker = new DefaultCircuitBreaker(delayedRemoteService, 3000, 2, 2000 * 1000 * 1000);

        QuickRemoteService quickRemoteService = new QuickRemoteService();
        DefaultCircuitBreaker quickServiceCircuitBreaker = new DefaultCircuitBreaker(quickRemoteService, 3000, 2, 2000 * 1000 * 1000);

        MonitoringService monitoringService = new MonitoringService(delayServiceCircuitBreaker, quickServiceCircuitBreaker);

        log.info(monitoringService.localResourceResponse());

        log.info(monitoringService.delayedServiceResponse());
        log.info(monitoringService.delayedServiceResponse());

        log.info(delayServiceCircuitBreaker.getState());

        log.info(monitoringService.quickServiceResponse());
        log.info(quickServiceCircuitBreaker.getState());

        try {
            log.info("Waiting for delay service to become responsive");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(delayServiceCircuitBreaker.getState());

        log.info(monitoringService.delayedServiceResponse());

        log.info(delayServiceCircuitBreaker.getState());
    }
}
