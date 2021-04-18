package org.kevin;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class Order {

    enum PaymentStatus {
        NOT_DONE,
        TRYING,
        DONE
    }

    enum MessageSent extends App {
        NONE_SENT,
        PAYMENT_FAIL,
        PAYMENT_TRYING,
        PAYMENT_SUCCESSFUL
    }

    final User user;

    final String item;

    public final String id;

    final float price;

    final long createdTime;

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private static final Map<String, Boolean> USE_IDS = new HashMap<>();

    PaymentStatus paid;

    MessageSent messageSent;

    boolean addedToEmployeeHandle;

    Order(User user, String item, float price) {
        this.createdTime = System.currentTimeMillis();
        this.user = user;
        this.item = item;
        this.price = price;
        String id = createUniqueId();
        if (USE_IDS.get(id) != null) {
            while (USE_IDS.get(id)) {
                id = createUniqueId();
            }
        }
        this.id = id;
        USE_IDS.put(this.id, true);
        this.paid = PaymentStatus.TRYING;
        this.messageSent = MessageSent.NONE_SENT;
        this.addedToEmployeeHandle = false;
    }

    private String createUniqueId() {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < 12) {
            int index = (int) (RANDOM.nextFloat() * ALL_CHARS.length());
            builder.append(ALL_CHARS.charAt(index));
        }
        return builder.toString();
    }
}
