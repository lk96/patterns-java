package org.kevin;

public class App {

    public static void main(String[] args) {
        FrontController controller = new FrontController();
        controller.handleRequest("Catapult");
        controller.handleRequest("Archer");
        controller.handleRequest("test");
    }
}
