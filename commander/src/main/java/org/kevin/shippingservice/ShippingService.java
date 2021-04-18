package org.kevin.shippingservice;


import lombok.AllArgsConstructor;
import org.kevin.Service;
import org.kevin.exceptions.DatabaseUnavailableException;

public class ShippingService extends Service {

    @AllArgsConstructor
    static class ShippingRequest {
        String transactionId;
        String item;
        String address;
    }

    public ShippingService(ShippingDatabase db, Exception... exc) {
        super(db, exc);
    }

    public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
        var id = generateId();
        var item = (String) parameters[0];
        var address = (String) parameters[1];
        var req = new ShippingRequest(id, item, address);
        return updateDb(req);
    }

    protected String updateDb(Object... parameters) throws DatabaseUnavailableException {
        var req = (ShippingRequest) parameters[0];
        if (this.database.get(req.transactionId) == null) {
            database.add(req);
            return req.transactionId;
        }
        return null;
    }
}