package org.kevin.prototype.adapter;

/**
 * 将一个接口转换成另一个客户期望的接口。让原本不兼容的接口的类可以合作
 */
public final class App {

    private App() {
    }

    public static void main(String[] args) {
        Captain captain = new Captain(new FinishingBoatAdapter());
        captain.row();
    }
}
