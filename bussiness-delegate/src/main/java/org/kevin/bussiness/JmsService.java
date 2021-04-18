package org.kevin.bussiness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmsService implements BusinessService{

    private static final Logger log = LoggerFactory.getLogger(JmsService.class);

    @Override
    public void doProcessing() {
        log.info("JmsService is processing...");
    }
}
