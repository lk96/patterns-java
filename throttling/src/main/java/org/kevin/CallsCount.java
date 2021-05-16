package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public final class CallsCount {

    private final Map<String, AtomicLong> tenantCallsCount = new ConcurrentHashMap<>();

    public void addTenant(String tenantName) {
        tenantCallsCount.putIfAbsent(tenantName, new AtomicLong(0));
    }

    public void incrementCount(String tenantName) {
        tenantCallsCount.get(tenantName).incrementAndGet();
    }

    public long getCount(String tenantName) {
        return tenantCallsCount.get(tenantName).get();
    }

    public void reset(){
        log.debug("Resetting the map");
        tenantCallsCount.replaceAll((k, v) -> new AtomicLong(0));
    }
}
