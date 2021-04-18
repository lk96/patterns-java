package org.kevin.employeehandle;

import org.kevin.Database;
import org.kevin.Order;
import org.kevin.Service;
import org.kevin.exceptions.DatabaseUnavailableException;

public class EmployeeHandle extends Service {

    public EmployeeHandle(Database database, Exception... exceptions) {
        super(database, exceptions);
    }

    @Override
    public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
        return updateDb(parameters[0]);
    }

    @Override
    protected String updateDb(Object... parameters) throws DatabaseUnavailableException {
        Order order = (Order)parameters[0];
        if (database.get(order.id) == null) {
            database.add(order);
            return order.id;
        }
        return null;
    }
}
