package org.kevin;

public class DefaultCircuitBreaker implements CircuitBreaker {

    private final long timeout;

    private final long retryTimePeriod;

    private final RemoteService remoteService;

    long lastFailureTime;

    private String lastFailureResponse;

    int failureCount;

    private final int failureThreshold;

    private State state;

    private final long futureTime = 1000L * 1000 * 1000 * 1000;


    public DefaultCircuitBreaker(RemoteService remoteService, long timeout, int failureThreshold, long retryTimePeriod) {
        this.timeout = timeout;
        this.retryTimePeriod = retryTimePeriod;
        this.remoteService = remoteService;
        this.failureThreshold = failureThreshold;
        this.state = State.CLOSED;
        this.lastFailureTime = System.nanoTime() + futureTime;
        this.failureCount = 0;
    }

    @Override
    public void recordSuccess() {
        this.failureCount = 0;
        this.lastFailureTime = System.nanoTime() + futureTime;
        this.state = State.CLOSED;
    }

    @Override
    public void recordFailure(String response) {
        failureCount += 1;
        this.lastFailureTime = System.nanoTime();
        this.lastFailureResponse = response;
    }

    protected void evaluateState() {
        if (failureCount >= failureThreshold) {
            if ((System.nanoTime() - lastFailureTime) > retryTimePeriod) {
                state = State.HALF_OPEN;
            } else {
                state = State.OPEN;
            }
        } else {
            state = State.CLOSED;
        }
    }

    @Override
    public String getState() {
        evaluateState();
        return state.name();
    }

    @Override
    public void setState(State state) {
        this.state = state;
        switch (state) {
            case OPEN:
                this.failureCount = failureThreshold;
                this.lastFailureTime = System.nanoTime();
                break;
            case HALF_OPEN:
                this.failureCount = failureThreshold;
                this.lastFailureTime = System.nanoTime() - retryTimePeriod;
                break;
            default:
                this.failureCount = 0;
        }
    }

    @Override
    public String attemptRequest() throws RemoteServiceException {
        evaluateState();
        if (state == State.OPEN) {
            return this.lastFailureResponse;
        } else {
            try {
                String response = remoteService.call();
                recordSuccess();
                return response;
            } catch (RemoteServiceException e) {
                recordFailure(e.getMessage());
                throw e;
            }
        }
    }
}
