package org.kevin.chain;

public class OrcCommander extends RequestHandler{

    public OrcCommander(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request request) {
        if (RequestType.DEFEND_CASTLE == request.getRequestType()) {
            printHandling(request);
            request.markHandled();
        }else {
            super.handleRequest(request);
        }
    }


    @Override
    public String toString() {
        return "Orc Commander";
    }
}
