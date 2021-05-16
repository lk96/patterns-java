package org.kevin;

import lombok.Getter;

import java.security.InvalidParameterException;

@Getter
public class Tenant {

    private final String name;

    private final int allowedCallsPerSecond;

    public Tenant(String name, int allowedCallsPerSecond,CallsCount callsCount) {
        if (allowedCallsPerSecond < 0) {
            throw new InvalidParameterException("Number of calls less than 0 not allowed");
        }
        this.name = name;
        this.allowedCallsPerSecond = allowedCallsPerSecond;
        callsCount.addTenant(name);
    }
}
