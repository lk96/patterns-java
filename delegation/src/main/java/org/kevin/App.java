package org.kevin;

import org.kevin.printers.CanonPrinter;
import org.kevin.printers.EpsonPrinter;
import org.kevin.printers.HpPrinter;

public class App {

    private static final String MESSAGE_TO_PRINT = "hello world";

    public static void main(String[] args) {
        PrinterController hpController = new PrinterController(new HpPrinter());
        PrinterController canonController = new PrinterController(new CanonPrinter());
        PrinterController epsonController = new PrinterController(new EpsonPrinter());

        hpController.print(MESSAGE_TO_PRINT);
        canonController.print(MESSAGE_TO_PRINT);
        epsonController.print(MESSAGE_TO_PRINT);
    }
}
