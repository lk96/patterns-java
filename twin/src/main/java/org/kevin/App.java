package org.kevin;

public class App {

    public static void main(String[] args) throws InterruptedException {

        BallItem ballItem = new BallItem();
        BallThread ballThread = new BallThread();

        ballItem.setTwin(ballThread);
        ballThread.setTwin(ballItem);
        ballThread.start();
        waiting();
        ballItem.click();
        waiting();
        ballItem.click();
        waiting();
        ballThread.stopMe();
    }

    private static void waiting() throws InterruptedException {
        Thread.sleep(500);
    }
}
