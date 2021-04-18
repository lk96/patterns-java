package org.kevin.bussiness;

public class App {

    public static void main(String[] args) {
        BusinessDelegate businessDelegate = new BusinessDelegate();
        BusinessLookup businessLookup = new BusinessLookup();
        businessLookup.setEjbService(new EjbService());
        businessLookup.setJmsService(new JmsService());
        businessDelegate.setBusinessLookup(businessLookup);
        businessDelegate.setServiceType(ServiceType.EJB);
        Client client = new Client(businessDelegate);
        client.doTask();
        businessDelegate.setServiceType(ServiceType.JMS);
        client.doTask();
    }
}
