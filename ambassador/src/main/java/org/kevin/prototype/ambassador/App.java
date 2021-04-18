package org.kevin.prototype.ambassador;

public class App {

    public static void main(String[] args) {
        Client client1 = new Client();
        Client client2 = new Client();
        client1.useService(12);
        client2.useService(73);
    }
}
