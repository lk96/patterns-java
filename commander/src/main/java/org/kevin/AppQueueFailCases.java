package org.kevin;

import org.kevin.employeehandle.EmployeeDatabase;
import org.kevin.employeehandle.EmployeeHandle;
import org.kevin.exceptions.DatabaseUnavailableException;
import org.kevin.exceptions.ItemUnavailableException;
import org.kevin.messagingservice.MessagingDatabase;
import org.kevin.messagingservice.MessagingService;
import org.kevin.paymentservice.PaymentDatabase;
import org.kevin.paymentservice.PaymentService;
import org.kevin.queue.QueueDatabase;
import org.kevin.shippingservice.ShippingDatabase;
import org.kevin.shippingservice.ShippingService;

/**
 * AppQueueFailCases class looks at possible cases when Queue Database is available/unavailable.
 */

public class AppQueueFailCases {
    private final int numOfRetries = 3;
    private final long retryDuration = 30000;
    private final long queueTime = 240000; //4 mins
    private final long queueTaskTime = 60000; //1 min
    private final long paymentTime = 120000; //2 mins
    private final long messageTime = 150000; //2.5 mins
    private final long employeeTime = 240000; //4 mins

    void queuePaymentTaskDatabaseUnavailableCase() throws Exception {
        var ps = new PaymentService(new PaymentDatabase(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException());
        var ss = new ShippingService(new ShippingDatabase());
        var ms = new MessagingService(new MessagingDatabase());
        var eh = new EmployeeHandle(new EmployeeDatabase());
        var qdb =
                new QueueDatabase(new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException(), new DatabaseUnavailableException());
        var c = new Commander(eh, ps, ss, ms, qdb, numOfRetries, retryDuration,
                queueTime, queueTaskTime, paymentTime, messageTime, employeeTime);
        var user = new User("Jim", "ABCD");
        var order = new Order(user, "book", 10f);
        c.placeOrder(order);
    }

    void queueMessageTaskDatabaseUnavailableCase() throws Exception {
        var ps = new PaymentService(new PaymentDatabase());
        var ss = new ShippingService(new ShippingDatabase());
        var ms = new MessagingService(new MessagingDatabase(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException());
        var eh = new EmployeeHandle(new EmployeeDatabase());
        var qdb =
                new QueueDatabase(new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException(), new DatabaseUnavailableException());
        var c = new Commander(eh, ps, ss, ms, qdb, numOfRetries, retryDuration,
                queueTime, queueTaskTime, paymentTime, messageTime, employeeTime);
        var user = new User("Jim", "ABCD");
        var order = new Order(user, "book", 10f);
        c.placeOrder(order);
    }

    void queueEmployeeDbTaskDatabaseUnavailableCase() throws Exception {
        var ps = new PaymentService(new PaymentDatabase());
        var ss = new ShippingService(new ShippingDatabase(), new ItemUnavailableException());
        var ms = new MessagingService(new MessagingDatabase());
        var eh = new EmployeeHandle(new EmployeeDatabase(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException());
        var qdb =
                new QueueDatabase(new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException(), new DatabaseUnavailableException());
        var c = new Commander(eh, ps, ss, ms, qdb, numOfRetries, retryDuration,
                queueTime, queueTaskTime, paymentTime, messageTime, employeeTime);
        var user = new User("Jim", "ABCD");
        var order = new Order(user, "book", 10f);
        c.placeOrder(order);
    }

    void queueSuccessCase() throws Exception {
        var ps = new PaymentService(new PaymentDatabase(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException(), new DatabaseUnavailableException(),
                new DatabaseUnavailableException());
        var ss = new ShippingService(new ShippingDatabase());
        var ms =
                new MessagingService(new MessagingDatabase(), new DatabaseUnavailableException(),
                        new DatabaseUnavailableException());
        var eh = new EmployeeHandle(new EmployeeDatabase());
        var qdb =
                new QueueDatabase(new DatabaseUnavailableException(), new DatabaseUnavailableException());
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
        var aqfc = new AppQueueFailCases();
        //aqfc.queuePaymentTaskDatabaseUnavailableCase();
        //aqfc.queueMessageTaskDatabaseUnavailableCase();
        //aqfc.queueEmployeeDbTaskDatabaseUnavailableCase();
        aqfc.queueSuccessCase();
    }
}
