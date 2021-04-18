package org.kevin.messagingservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kevin.Database;
import org.kevin.Service;
import org.kevin.exceptions.DatabaseUnavailableException;

@Slf4j
public class MessagingService extends Service {

    enum MessageToSend {
        PAYMENT_FAIl,
        PAYMENT_TRYING,
        PAYMENT_SUCCESSFUL
    }

    @RequiredArgsConstructor
    static class MessageRequest {
        final String reqId;
        final MessageToSend msg;
    }

    public MessagingService(Database database, Exception... exceptions) {
        super(database, exceptions);
    }

    @Override
    public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
        int messageToSend = (int) parameters[0];
        String id = generateId();
        MessageToSend msg;
        if (messageToSend == 0) {
            msg = MessageToSend.PAYMENT_FAIl;
        } else if (messageToSend == 1) {
            msg = MessageToSend.PAYMENT_TRYING;
        } else {
            msg = MessageToSend.PAYMENT_SUCCESSFUL;
        }
        MessageRequest messageRequest = new MessageRequest(id, msg);
        return updateDb(messageRequest);
    }

    @Override
    protected String updateDb(Object... parameters) throws DatabaseUnavailableException {
        MessageRequest parameter = (MessageRequest) parameters[0];
        if (this.database.get(parameter.reqId) == null) {
            database.add(parameter);
            log.info(sendMessage(parameter.msg));
            return parameter.reqId;
        }
        return null;
    }

    String sendMessage(MessageToSend m) {
        if (m.equals(MessageToSend.PAYMENT_SUCCESSFUL)) {
            return "Msg: Your order has been placed and paid for successfully!"
                    + " Thank you for shopping with us!";
        } else if (m.equals(MessageToSend.PAYMENT_TRYING)) {
            return "Msg: There was an error in your payment process,"
                    + " we are working on it and will return back to you shortly."
                    + " Meanwhile, your order has been placed and will be shipped.";
        } else {
            return "Msg: There was an error in your payment process."
                    + " Your order is placed and has been converted to COD."
                    + " Please reach us on CUSTOMER-CARE-NUBER in case of any queries."
                    + " Thank you for shopping with us!";
        }
    }
}
