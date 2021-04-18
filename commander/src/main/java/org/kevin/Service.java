package org.kevin;

import org.kevin.exceptions.DatabaseUnavailableException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class Service {

    protected final Database database;

    public ArrayList<Exception> exceptionsList;

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final String ALL_CHARTS="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private static final Hashtable<String, Boolean> USED_IDS = new Hashtable<>();

    protected Service(Database database, Exception... exceptions) {
        this.database = database;
        this.exceptionsList = new ArrayList<>(List.of(exceptions));
    }

    public abstract String receiveRequest(Object... parameters) throws DatabaseUnavailableException;

    protected abstract String updateDb(Object... parameters) throws DatabaseUnavailableException;

    protected String generateId(){
        StringBuilder builder = new StringBuilder();
        while (builder.length() < 12) {
            int index = (int) (RANDOM.nextFloat() * ALL_CHARTS.length());
            builder.append(ALL_CHARTS.charAt(index));
        }
        String id = builder.toString();
        if (USED_IDS.get(id) != null) {
            while (USED_IDS.get(id)) {
                id = generateId();
            }
        }
        return id;
    }
}
