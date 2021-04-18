package org.kevin.prototype.ambassador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

public class ServiceAmbassador implements IRemoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAmbassador.class);
    private static final int RETRIES = 3;
    private static final int DELAY_MS = 3000;

    public ServiceAmbassador() {
    }

    @Override
    public long doRemoteFunction(int value) {
        return safeCall(value);
    }

    private long safeCall(int value) {
        int retries = 0;
        long result = RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue();
        for (int i = 0; i < RETRIES; i++) {
            if (retries >= RETRIES) {
                return RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue();
            }
            if ((result = checkLatency(value)) == RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue()) {
                LOGGER.info("Failed to reach remote:(" + (i + 1) + ")");
                retries++;
                try {
                    sleep(DELAY_MS);
                } catch (InterruptedException e) {
                    LOGGER.info("Thread sleep state interrupted", e);
                }
            } else {
                break;
            }
        }
        return result;
    }

    private long checkLatency(int value) {
        long start = System.currentTimeMillis();
        long result = RemoteService.getRemoteService().doRemoteFunction(value);
        long timeTaken = System.currentTimeMillis() - start;
        LOGGER.info("Time taken (ms):" + timeTaken);
        return result;
    }
}
