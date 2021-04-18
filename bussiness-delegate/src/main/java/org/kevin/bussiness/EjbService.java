package org.kevin.bussiness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EjbService implements BusinessService {

    private static final Logger log = LoggerFactory.getLogger(EjbService.class);

    @Override
    public void doProcessing() {
        log.info("EjbService is processing");
    }
}
