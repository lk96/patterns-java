package org.kevin.chain;

public class OrcOfficer extends RequestHandler{

    public OrcOfficer(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request request) {
        if (RequestType.TORTURE_PRISONER == request.getRequestType()) {
            printHandling(request);
            request.markHandled();
        }else {
            super.handleRequest(request);
        }
    }

    @Override
    public String toString() {
        return "Orc officer";
    }
}
