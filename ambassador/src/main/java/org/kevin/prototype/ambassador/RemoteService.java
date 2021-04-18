package org.kevin.prototype.ambassador;

import org.kevin.prototype.ambassador.util.RandomProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

public class RemoteService implements IRemoteService {

    private static final int THRESHOLD = 200;
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteService.class);
    private static RemoteService service = null;
    private final RandomProvider randomProvider;

    static synchronized RemoteService getRemoteService() {
        if (service == null) {
            service = new RemoteService();
        }
        return service;
    }

    private RemoteService() {
        this(Math::random);
    }

    public RemoteService(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    @Override
    public long doRemoteFunction(int value) {
        long waitTime = (long) Math.floor(randomProvider.random() * 1000);
        try {
            sleep(waitTime);
        } catch (InterruptedException e) {
            LOGGER.info("Thread sleep state interrupted", e);
        }
        return waitTime <= THRESHOLD ? value * 10 : RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue();
    }
}
