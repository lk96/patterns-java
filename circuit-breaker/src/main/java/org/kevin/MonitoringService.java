package org.kevin;

public class MonitoringService {

    private final CircuitBreaker delayedService;

    private final CircuitBreaker quickService;

    public MonitoringService(CircuitBreaker delayedService, CircuitBreaker quickService) {
        this.delayedService = delayedService;
        this.quickService = quickService;
    }

    public String localResourceResponse() {
        return "Local Service is working";
    }

    public String delayedServiceResponse() {
        try {
            return this.delayedService.attemptRequest();
        } catch (RemoteServiceException e) {
            return e.getMessage();
        }
    }

    public String quickServiceResponse() {
        try {
            return this.quickService.attemptRequest();
        } catch (RemoteServiceException e) {
            return e.getMessage();
        }
    }
}
