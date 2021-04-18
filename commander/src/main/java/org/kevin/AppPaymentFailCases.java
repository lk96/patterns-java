package org.kevin;

import org.kevin.employeehandle.EmployeeDatabase;
import org.kevin.employeehandle.EmployeeHandle;
import org.kevin.exceptions.DatabaseUnavailableException;
import org.kevin.exceptions.PaymentDetailsErrorException;
import org.kevin.messagingservice.MessagingDatabase;
import org.kevin.messagingservice.MessagingService;
import org.kevin.paymentservice.PaymentDatabase;
import org.kevin.paymentservice.PaymentService;
import org.kevin.queue.QueueDatabase;
import org.kevin.shippingservice.ShippingDatabase;
import org.kevin.shippingservice.ShippingService;

public class AppPaymentFailCases {
    private final int numOfRetries = 3;
    private final long retryDuration = 30000;
    private final long queueTime = 240000; //4 mins
    private final long queueTaskTime = 60000; //1 min
    private final long paymentTime = 120000; //2 mins
    private final long messageTime = 150000; //2.5 mins
    private final long employeeTime = 240000; //4 mins

    void paymentNotPossibleCase() throws Exception {
        var ps = new PaymentService(new PaymentDatabase(), new DatabaseUnavailableException(),
                new PaymentDetailsErrorException());
        var ss = new ShippingService(new ShippingDatabase());
        var ms = new MessagingService(new MessagingDatabase(), new DatabaseUnavailableException());
        var eh = new EmployeeHandle(new EmployeeDatabase());
        var qdb = new QueueDatabase(new DatabaseUnavailableException());
        var c = new Commander(eh, ps, ss, ms, qdb, numOfRetries, retryDuration,
                queueTime, queueTaskTime, paymentTime, messageTime, employeeTime);
        var user = new User("Jim", "ABCD");
        var order = new Order(user, "book", 10f);
        c.placeOrder(order);
    }

    void paymentDatabaseUnavailableCase() throws Exception {
        //rest is successful
        var ps = new PaymentService(new PaymentDatabase(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException());
        var ss = new ShippingService(new ShippingDatabase());
        var ms = new MessagingService(new MessagingDatabase());
        var eh = new EmployeeHandle(new EmployeeDatabase());
        var qdb = new QueueDatabase();
        var c = new Commander(eh, ps, ss, ms, qdb, numOfRetries, retryDuration,
                queueTime, queueTaskTime, paymentTime, messageTime, employeeTime);
        var user = new User("Jim", "ABCD");
        var order = new Order(user, "book", 10f);
        c.placeOrder(order);
    }

    void paymentSuccessCase() throws Exception {
        //goes to message after 2 retries maybe - rest is successful for now
        var ps = new PaymentService(new PaymentDatabase(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException());
        var ss = new ShippingService(new ShippingDatabase());
        var ms =
                new MessagingService(new MessagingDatabase(), new DatabaseUnavailableException());
        var eh = new EmployeeHandle(new EmployeeDatabase());
        var qdb = new QueueDatabase(new DatabaseUnavailableException());
        var c = new Commander(eh, ps, ss, ms, qdb, numOfRetries, retryDuration,
                queueTime, queueTaskTime, paymentTime, messageTime, employeeTime);
        var user = new User("Jim", "ABCD");
        var order = new Order(user, "book", 10f);
        c.placeOrder(order);
    }

    /**
     * Program entry point.
     *
     * @param args command line args
     */

    public static void main(String[] args) throws Exception {
        var apfc = new AppPaymentFailCases();
        //apfc.paymentNotPossibleCase();
        //apfc.paymentDatabaseUnavailableCase();
        apfc.paymentSuccessCase();
    }
}