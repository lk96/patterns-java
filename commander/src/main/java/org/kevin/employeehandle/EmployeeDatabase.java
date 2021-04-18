package org.kevin.employeehandle;

import org.kevin.Database;
import org.kevin.Order;
import org.kevin.exceptions.DatabaseUnavailableException;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDatabase extends Database<Order> {

    private final Map<String, Order> data = new HashMap<>();

    @Override
    public Order add(Order obj) throws DatabaseUnavailableException {
        return data.put(obj.id, obj);
    }

    @Override
    public Order get(String id) throws DatabaseUnavailableException {
        return data.get(id);
    }
}
