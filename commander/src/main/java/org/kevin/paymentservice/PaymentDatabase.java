package org.kevin.paymentservice;

import org.kevin.Database;

import java.util.Hashtable;
import java.util.Map;

public class PaymentDatabase extends Database<PaymentService.PaymentRequest> {

    //0-fail, 1-error, 2-success
    private final Map<String, PaymentService.PaymentRequest> data = new Hashtable<>();

    @Override
    public PaymentService.PaymentRequest add(PaymentService.PaymentRequest r) {
        return data.put(r.transactionId, r);
    }

    @Override
    public PaymentService.PaymentRequest get(String requestId) {
        return data.get(requestId);
    }

}
