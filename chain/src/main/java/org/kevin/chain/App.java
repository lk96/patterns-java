package org.kevin.chain;

public class App {

    public static void main(String[] args) {
        OrcKing orcKing = new OrcKing();
        orcKing.makeRequest(new Request(RequestType.DEFEND_CASTLE,"defend castle"));
        orcKing.makeRequest(new Request(RequestType.COLLECT_TAX,"collect tax"));
        orcKing.makeRequest(new Request(RequestType.TORTURE_PRISONER,"torture prisoner"));
    }
}
