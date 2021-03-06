package org.kevin.prototype.ambassador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    private final ServiceAmbassador serviceAmbassador = new ServiceAmbassador();

    long useService(int value){
        long result = serviceAmbassador.doRemoteFunction(value);
        LOGGER.info("Service result:"+result);
        return result;
    }
}
