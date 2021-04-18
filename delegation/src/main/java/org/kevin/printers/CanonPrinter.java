package org.kevin.printers;

import lombok.extern.slf4j.Slf4j;
import org.kevin.Printer;

@Slf4j
public class CanonPrinter implements Printer {

    @Override
    public void print(String message) {
        log.info("canon printer :{}", message);
    }
}
