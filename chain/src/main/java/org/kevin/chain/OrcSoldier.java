package org.kevin.chain;

public class OrcSoldier extends RequestHandler {

    public OrcSoldier(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request request) {
        if (RequestType.COLLECT_TAX == request.getRequestType()) {
            printHandling(request);
            request.markHandled();
        } else {
            super.handleRequest(request);
        }
    }


    @Override
    public String toString() {
        return "Orc solider";
    }
}
