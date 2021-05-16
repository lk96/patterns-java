package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class B2BService {

    private final CallsCount callsCount;

    public B2BService(Throttler timer, CallsCount callsCount) {
        this.callsCount = callsCount;
        timer.start();
    }

    public int dummyCustomerApi(Tenant tenant) {
        String tenantName = tenant.getName();
        long count = callsCount.getCount(tenantName);
        log.debug("Counter for {} : {}",tenantName,count);
        if (count >= tenant.getAllowedCallsPerSecond()) {
            log.error("API access  per second limit reached for:{}", tenantName);
            return -1;
        }
        callsCount.incrementCount(tenantName);
        return getRandomCustomerId();
    }

    private int getRandomCustomerId() {
        return ThreadLocalRandom.current().nextInt(1,1000);
    }
}
