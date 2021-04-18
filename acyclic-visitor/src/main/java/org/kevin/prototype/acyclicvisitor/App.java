package org.kevin.prototype.acyclicvisitor;

public class App {

    public static void main(String[] args) {
        ConfigureForUnixVisitor unixVisitor = new ConfigureForUnixVisitor();
        ConfigureForDosVisitor dosVisitor = new ConfigureForDosVisitor();
        Zoom zoom = new Zoom();
        Hayes hayes = new Hayes();
        hayes.accept(dosVisitor);
        zoom.accept(dosVisitor);
        zoom.accept(unixVisitor);
        hayes.accept(unixVisitor);
    }
}
