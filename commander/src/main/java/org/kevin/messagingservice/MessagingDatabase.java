package org.kevin.messagingservice;

import org.kevin.Database;
import org.kevin.exceptions.DatabaseUnavailableException;

import java.util.Hashtable;
import java.util.Map;

public class MessagingDatabase extends Database<MessagingService.MessageRequest> {

    private final Map<String, MessagingService.MessageRequest> data = new Hashtable<>();

    @Override
    public MessagingService.MessageRequest add(MessagingService.MessageRequest obj) throws DatabaseUnavailableException {
        return data.put(obj.reqId, obj);
    }

    @Override
    public MessagingService.MessageRequest get(String id) throws DatabaseUnavailableException {
        return data.get(id);
    }
}
