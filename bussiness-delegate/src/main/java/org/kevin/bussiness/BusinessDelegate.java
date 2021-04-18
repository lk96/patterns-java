package org.kevin.bussiness;

public class BusinessDelegate {

    private BusinessLookup businessLookup;

    private BusinessService businessService;

    private ServiceType serviceType;

    public void setBusinessLookup(BusinessLookup businessLookup) {
        this.businessLookup = businessLookup;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public void doTask() {
        businessService = businessLookup.getBusinessService(serviceType);
        businessService.doProcessing();
    }
}
