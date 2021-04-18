package org.kevin;

import org.kevin.Order.MessageSent;
import org.kevin.Order.PaymentStatus;
import org.kevin.employeehandle.EmployeeHandle;
import org.kevin.exceptions.DatabaseUnavailableException;
import org.kevin.exceptions.ItemUnavailableException;
import org.kevin.exceptions.PaymentDetailsErrorException;
import org.kevin.exceptions.ShippingNotPossibleException;
import org.kevin.messagingservice.MessagingService;
import org.kevin.paymentservice.PaymentService;
import org.kevin.queue.QueueDatabase;
import org.kevin.queue.QueueTask;
import org.kevin.queue.QueueTask.TaskType;
import org.kevin.shippingservice.ShippingService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Commander {

    private final QueueDatabase queue;
    private final EmployeeHandle employeeDb;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final MessagingService messagingService;
    private int queueItems = 0; //keeping track here only so don't need access to queue db to get this
    private final int numOfRetries;
    private final long retryDuration;
    private final long queueTime;
    private final long queueTaskTime;
    private final long paymentTime;
    private final long messageTime;
    private final long employeeTime;
    private boolean finalSiteMsgShown;
    private static final Logger LOG = LoggerFactory.getLogger(Commander.class);
    //we could also have another db where it stores all orders

    Commander(EmployeeHandle empDb, PaymentService paymentService, ShippingService shippingService,
              MessagingService messagingService, QueueDatabase qdb, int numOfRetries,
              long retryDuration, long queueTime, long queueTaskTime, long paymentTime,
              long messageTime, long employeeTime) {
        this.paymentService = paymentService;
        this.shippingService = shippingService;
        this.messagingService = messagingService;
        this.employeeDb = empDb;
        this.queue = qdb;
        this.numOfRetries = numOfRetries;
        this.retryDuration = retryDuration;
        this.queueTime = queueTime;
        this.queueTaskTime = queueTaskTime;
        this.paymentTime = paymentTime;
        this.messageTime = messageTime;
        this.employeeTime = employeeTime;
        this.finalSiteMsgShown = false;
    }

    void placeOrder(Order order) throws Exception {
        sendShippingRequest(order);
    }

    private void sendShippingRequest(Order order) throws Exception {
        var list = shippingService.exceptionsList;
        Retry.Operation op = (l) -> {
            if (!l.isEmpty()) {
                if (DatabaseUnavailableException.class.isAssignableFrom(l.get(0).getClass())) {
                    LOG.debug("Order " + order.id + ": Error in connecting to shipping service, "
                            + "trying again..");
                } else {
                    LOG.debug("Order " + order.id + ": Error in creating shipping request..");
                }
                throw l.remove(0);
            }
            String transactionId = shippingService.receiveRequest(order.item, order.user.address);
            //could save this transaction id in a db too
            LOG.info("Order " + order.id + ": Shipping placed successfully, transaction id: "
                    + transactionId);
            LOG.info("Order has been placed and will be shipped to you. Please wait while we make your"
                    + " payment... ");
            sendPaymentRequest(order);
        };
        Retry.HandleErrorIssue<Order> handleError = (o, err) -> {
            if (ShippingNotPossibleException.class.isAssignableFrom(err.getClass())) {
                LOG.info("Shipping is currently not possible to your address. We are working on the problem"
                        + " and will get back to you asap.");
                finalSiteMsgShown = true;
                LOG.info("Order " + order.id + ": Shipping not possible to address, trying to add problem "
                        + "to employee db..");
                employeeHandleIssue(o);
            } else if (ItemUnavailableException.class.isAssignableFrom(err.getClass())) {
                LOG.info("This item is currently unavailable. We will inform you as soon as the item "
                        + "becomes available again.");
                finalSiteMsgShown = true;
                LOG.info("Order " + order.id + ": Item " + order.item + " unavailable, trying to add "
                        + "problem to employee handle..");
                employeeHandleIssue(o);
            } else {
                LOG.info("Sorry, there was a problem in creating your order. Please try later.");
                LOG.error("Order " + order.id + ": Shipping service unavailable, order not placed..");
                finalSiteMsgShown = true;
            }
        };
        var r = new Retry<>(op, handleError, numOfRetries, retryDuration,
                e -> DatabaseUnavailableException.class.isAssignableFrom(e.getClass()));
        r.perform(list, order);
    }

    private void sendPaymentRequest(Order order) {
        if (System.currentTimeMillis() - order.createdTime >= this.paymentTime) {
            if (order.paid.equals(PaymentStatus.TRYING)) {
                order.paid = PaymentStatus.NOT_DONE;
                sendPaymentFailureMessage(order);
                LOG.error("Order " + order.id + ": Payment time for order over, failed and returning..");
            } //if succeeded or failed, would have been dequeued, no attempt to make payment
            return;
        }
        var list = paymentService.exceptionsList;
        var t = new Thread(() -> {
            Retry.Operation op = (l) -> {
                if (!l.isEmpty()) {
                    if (DatabaseUnavailableException.class.isAssignableFrom(l.get(0).getClass())) {
                        LOG.debug("Order " + order.id + ": Error in connecting to payment service,"
                                + " trying again..");
                    } else {
                        LOG.debug("Order " + order.id + ": Error in creating payment request..");
                    }
                    throw l.remove(0);
                }
                if (order.paid.equals(PaymentStatus.TRYING)) {
                    var transactionId = paymentService.receiveRequest(order.price);
                    order.paid = PaymentStatus.DONE;
                    LOG.info("Order " + order.id + ": Payment successful, transaction Id: " + transactionId);
                    if (!finalSiteMsgShown) {
                        LOG.info("Payment made successfully, thank you for shopping with us!!");
                        finalSiteMsgShown = true;
                    }
                    sendSuccessMessage(order);
                }
            };
            Retry.HandleErrorIssue<Order> handleError = (o, err) -> {
                if (PaymentDetailsErrorException.class.isAssignableFrom(err.getClass())) {
                    if (!finalSiteMsgShown) {
                        LOG.info("There was an error in payment. Your account/card details "
                                + "may have been incorrect. "
                                + "Meanwhile, your order has been converted to COD and will be shipped.");
                        finalSiteMsgShown = true;
                    }
                    LOG.error("Order " + order.id + ": Payment details incorrect, failed..");
                    o.paid = PaymentStatus.NOT_DONE;
                    sendPaymentFailureMessage(o);
                } else {
                    if (o.messageSent.equals(MessageSent.NONE_SENT)) {
                        if (!finalSiteMsgShown) {
                            LOG.info("There was an error in payment. We are on it, and will get back to you "
                                    + "asap. Don't worry, your order has been placed and will be shipped.");
                            finalSiteMsgShown = true;
                        }
                        LOG.warn("Order " + order.id + ": Payment error, going to queue..");
                        sendPaymentPossibleErrorMsg(o);
                    }
                    if (o.paid.equals(PaymentStatus.TRYING) && System
                            .currentTimeMillis() - o.createdTime < paymentTime) {
                        var qt = new QueueTask(o, TaskType.PAYMENT, -1);
                        updateQueue(qt);
                    }
                }
            };
            var r = new Retry<>(op, handleError, numOfRetries, retryDuration,
                    e -> DatabaseUnavailableException.class.isAssignableFrom(e.getClass()));
            try {
                r.perform(list, order);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        t.start();
    }

    private void updateQueue(QueueTask qt) {
        if (System.currentTimeMillis() - qt.order.createdTime >= this.queueTime) {
            // since payment time is lesser than queuetime it would have already failed..
            // additional check not needed
            LOG.trace("Order " + qt.order.id + ": Queue time for order over, failed..");
            return;
        } else if (qt.taskType.equals(TaskType.PAYMENT) && !qt.order.paid.equals(PaymentStatus.TRYING)
                || qt.taskType.equals(TaskType.MESSAGING) && (qt.messageType == 1
                && !qt.order.messageSent.equals(MessageSent.NONE_SENT)
                || qt.order.messageSent.equals(MessageSent.PAYMENT_FAIL)
                || qt.order.messageSent.equals(MessageSent.PAYMENT_SUCCESSFUL))
                || qt.taskType.equals(TaskType.EMPLOYEE_DB) && qt.order.addedToEmployeeHandle) {
            LOG.trace("Order " + qt.order.id + ": Not queueing task since task already done..");
            return;
        }
        var list = queue.exceptionsList;
        Thread t = new Thread(() -> {
            Retry.Operation op = (list1) -> {
                if (!list1.isEmpty()) {
                    LOG.warn("Order " + qt.order.id + ": Error in connecting to queue db, trying again..");
                    throw list1.remove(0);
                }
                queue.add(qt);
                queueItems++;
                LOG.info("Order " + qt.order.id + ": " + qt.getType() + " task enqueued..");
                tryDoingTasksInQueue();
            };
            Retry.HandleErrorIssue<QueueTask> handleError = (qt1, err) -> {
                if (qt1.taskType.equals(TaskType.PAYMENT)) {
                    qt1.order.paid = PaymentStatus.NOT_DONE;
                    sendPaymentFailureMessage(qt1.order);
                    LOG.error("Order " + qt1.order.id + ": Unable to enqueue payment task,"
                            + " payment failed..");
                }
                LOG.error("Order " + qt1.order.id + ": Unable to enqueue task of type " + qt1.getType()
                        + ", trying to add to employee handle..");
                employeeHandleIssue(qt1.order);
            };
            var r = new Retry<>(op, handleError, numOfRetries, retryDuration,
                    e -> DatabaseUnavailableException.class.isAssignableFrom(e.getClass()));
            try {
                r.perform(list, qt);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        t.start();
    }

    private void tryDoingTasksInQueue() { //commander controls operations done to queue
        var list = queue.exceptionsList;
        var t2 = new Thread(() -> {
            Retry.Operation op = (list1) -> {
                if (!list1.isEmpty()) {
                    LOG.warn("Error in accessing queue db to do tasks, trying again..");
                    throw list1.remove(0);
                }
                doTasksInQueue();
            };
            Retry.HandleErrorIssue<QueueTask> handleError = (o, err) -> {
            };
            var r = new Retry<>(op, handleError, numOfRetries, retryDuration,
                    e -> DatabaseUnavailableException.class.isAssignableFrom(e.getClass()));
            try {
                r.perform(list, null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        t2.start();
    }

    private void tryDequeue() {
        var list = queue.exceptionsList;
        var t3 = new Thread(() -> {
            Retry.Operation op = (list1) -> {
                if (!list1.isEmpty()) {
                    LOG.warn("Error in accessing queue db to dequeue task, trying again..");
                    throw list1.remove(0);
                }
                queue.dequeue();
                queueItems--;
            };
            Retry.HandleErrorIssue<QueueTask> handleError = (o, err) -> {
            };
            var r = new Retry<QueueTask>(op, handleError, numOfRetries, retryDuration,
                    e -> DatabaseUnavailableException.class.isAssignableFrom(e.getClass()));
            try {
                r.perform(list, null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        t3.start();
    }

    private void sendSuccessMessage(Order order) {
        if (System.currentTimeMillis() - order.createdTime >= this.messageTime) {
            LOG.trace("Order " + order.id + ": Message time for order over, returning..");
            return;
        }
        var list = messagingService.exceptionsList;
        Thread t = new Thread(() -> {
            Retry.Operation op = handleSuccessMessageRetryOperation(order);
            Retry.HandleErrorIssue<Order> handleError = (o, err) -> {
                handleSuccessMessageErrorIssue(order, o);
            };
            var r = new Retry<>(op, handleError, numOfRetries, retryDuration,
                    e -> DatabaseUnavailableException.class.isAssignableFrom(e.getClass()));
            try {
                r.perform(list, order);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        t.start();
    }

    private void handleSuccessMessageErrorIssue(Order order, Order o) {
        if ((o.messageSent.equals(MessageSent.NONE_SENT) || o.messageSent
                .equals(MessageSent.PAYMENT_TRYING))
                && System.currentTimeMillis() - o.createdTime < messageTime) {
            var qt = new QueueTask(order, TaskType.MESSAGING, 2);
            updateQueue(qt);
            LOG.info("Order " + order.id + ": Error in sending Payment Success message, trying to"
                    + " queue task and add to employee handle..");
            employeeHandleIssue(order);
        }
    }

    private Retry.Operation handleSuccessMessageRetryOperation(Order order) {
        return (l) -> {
            if (!l.isEmpty()) {
                if (DatabaseUnavailableException.class.isAssignableFrom(l.get(0).getClass())) {
                    LOG.debug("Order " + order.id + ": Error in connecting to messaging service "
                            + "(Payment Success msg), trying again..");
                } else {
                    LOG.debug("Order " + order.id + ": Error in creating Payment Success"
                            + " messaging request..");
                }
                throw l.remove(0);
            }
            if (!order.messageSent.equals(MessageSent.PAYMENT_FAIL)
                    && !order.messageSent.equals(MessageSent.PAYMENT_SUCCESSFUL)) {
                var requestId = messagingService.receiveRequest(2);
                order.messageSent = MessageSent.PAYMENT_SUCCESSFUL;
                LOG.info("Order " + order.id + ": Payment Success message sent,"
                        + " request Id: " + requestId);
            }
        };
    }

    private void sendPaymentFailureMessage(Order order) {
        if (System.currentTimeMillis() - order.createdTime >= this.messageTime) {
            LOG.trace("Order " + order.id + ": Message time for order over, returning..");
            return;
        }
        var list = messagingService.exceptionsList;
        var t = new Thread(() -> {
            Retry.Operation op = (l) -> {
                handlePaymentFailureRetryOperation(order, l);
            };
            Retry.HandleErrorIssue<Order> handleError = (o, err) -> {
                handlePaymentErrorIssue(order, o);
            };
            var r = new Retry<>(op, handleError, numOfRetries, retryDuration,
                    e -> DatabaseUnavailableException.class.isAssignableFrom(e.getClass()));
            try {
                r.perform(list, order);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        t.start();
    }

    private void handlePaymentErrorIssue(Order order, Order o) {
        if ((o.messageSent.equals(MessageSent.NONE_SENT) || o.messageSent
                .equals(MessageSent.PAYMENT_TRYING))
                && System.currentTimeMillis() - o.createdTime < messageTime) {
            var qt = new QueueTask(order, TaskType.MESSAGING, 0);
            updateQueue(qt);
            LOG.warn("Order " + order.id + ": Error in sending Payment Failure message, "
                    + "trying to queue task and add to employee handle..");
            employeeHandleIssue(o);
        }
    }

    private void handlePaymentFailureRetryOperation(Order order, List<Exception> l) throws Exception {
        if (!l.isEmpty()) {
            if (DatabaseUnavailableException.class.isAssignableFrom(l.get(0).getClass())) {
                LOG.debug("Order " + order.id + ": Error in connecting to messaging service "
                        + "(Payment Failure msg), trying again..");
            } else {
                LOG.debug("Order " + order.id + ": Error in creating Payment Failure"
                        + " message request..");
            }
            throw l.remove(0);
        }
        if (!order.messageSent.equals(MessageSent.PAYMENT_FAIL)
                && !order.messageSent.equals(MessageSent.PAYMENT_SUCCESSFUL)) {
            var requestId = messagingService.receiveRequest(0);
            order.messageSent = MessageSent.PAYMENT_FAIL;
            LOG.info("Order " + order.id + ": Payment Failure message sent successfully,"
                    + " request Id: " + requestId);
        }
    }

    private void sendPaymentPossibleErrorMsg(Order order) {
        if (System.currentTimeMillis() - order.createdTime >= this.messageTime) {
            LOG.trace("Message time for order over, returning..");
            return;
        }
        var list = messagingService.exceptionsList;
        var t = new Thread(() -> {
            Retry.Operation op = (l) -> {
                handlePaymentPossibleErrorMsgRetryOperation(order, l);
            };
            Retry.HandleErrorIssue<Order> handleError = (o, err) -> {
                handlePaymentPossibleErrorMsgErrorIssue(order, o);
            };
            var r = new Retry<>(op, handleError, numOfRetries, retryDuration,
                    e -> DatabaseUnavailableException.class.isAssignableFrom(e.getClass()));
            try {
                r.perform(list, order);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        t.start();
    }

    private void handlePaymentPossibleErrorMsgErrorIssue(Order order, Order o) {
        if (o.messageSent.equals(MessageSent.NONE_SENT) && order.paid
                .equals(PaymentStatus.TRYING)
                && System.currentTimeMillis() - o.createdTime < messageTime) {
            var qt = new QueueTask(order, TaskType.MESSAGING, 1);
            updateQueue(qt);
            LOG.warn("Order " + order.id + ": Error in sending Payment Error message, "
                    + "trying to queue task and add to employee handle..");
            employeeHandleIssue(o);
        }
    }

    private void handlePaymentPossibleErrorMsgRetryOperation(Order order, List<Exception> l)
            throws Exception {
        if (!l.isEmpty()) {
            if (DatabaseUnavailableException.class.isAssignableFrom(l.get(0).getClass())) {
                LOG.debug("Order " + order.id + ": Error in connecting to messaging service "
                        + "(Payment Error msg), trying again..");
            } else {
                LOG.debug("Order " + order.id + ": Error in creating Payment Error"
                        + " messaging request..");
            }
            throw l.remove(0);
        }
        if (order.paid.equals(PaymentStatus.TRYING) && order.messageSent
                .equals(MessageSent.NONE_SENT)) {
            var requestId = messagingService.receiveRequest(1);
            order.messageSent = MessageSent.PAYMENT_TRYING;
            LOG.info("Order " + order.id + ": Payment Error message sent successfully,"
                    + " request Id: " + requestId);
        }
    }

    private void employeeHandleIssue(Order order) {
        if (System.currentTimeMillis() - order.createdTime >= this.employeeTime) {
            LOG.trace("Order " + order.id + ": Employee handle time for order over, returning..");
            return;
        }
        var list = employeeDb.exceptionsList;
        var t = new Thread(() -> {
            Retry.Operation op = (l) -> {
                if (!l.isEmpty()) {
                    LOG.warn("Order " + order.id + ": Error in connecting to employee handle,"
                            + " trying again..");
                    throw l.remove(0);
                }
                if (!order.addedToEmployeeHandle) {
                    employeeDb.receiveRequest(order);
                    order.addedToEmployeeHandle = true;
                    LOG.info("Order " + order.id + ": Added order to employee database");
                }
            };
            Retry.HandleErrorIssue<Order> handleError = (o, err) -> {
                if (!o.addedToEmployeeHandle && System
                        .currentTimeMillis() - order.createdTime < employeeTime) {
                    var qt = new QueueTask(order, TaskType.EMPLOYEE_DB, -1);
                    updateQueue(qt);
                    LOG.warn("Order " + order.id + ": Error in adding to employee db,"
                            + " trying to queue task..");
                }
            };
            var r = new Retry<>(op, handleError, numOfRetries, retryDuration,
                    e -> DatabaseUnavailableException.class.isAssignableFrom(e.getClass()));
            try {
                r.perform(list, order);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        t.start();
    }

    private void doTasksInQueue() throws Exception {
        if (queueItems != 0) {
            var qt = queue.peek(); //this should probably be cloned here
            //this is why we have retry for doTasksInQueue
            LOG.trace("Order " + qt.order.id + ": Started doing task of type " + qt.getType());
            if (qt.getFirstAttemptTime() == -1) {
                qt.setFirstAttemptTime(System.currentTimeMillis());
            }
            if (System.currentTimeMillis() - qt.getFirstAttemptTime() >= queueTaskTime) {
                tryDequeue();
                LOG.trace("Order " + qt.order.id + ": This queue task of type " + qt.getType()
                        + " does not need to be done anymore (timeout), dequeue..");
            } else {
                if (qt.taskType.equals(TaskType.PAYMENT)) {
                    if (!qt.order.paid.equals(PaymentStatus.TRYING)) {
                        tryDequeue();
                        LOG.trace("Order " + qt.order.id + ": This payment task already done, dequeueing..");
                    } else {
                        sendPaymentRequest(qt.order);
                        LOG.debug("Order " + qt.order.id + ": Trying to connect to payment service..");
                    }
                } else if (qt.taskType.equals(TaskType.MESSAGING)) {
                    if (qt.order.messageSent.equals(MessageSent.PAYMENT_FAIL)
                            || qt.order.messageSent.equals(MessageSent.PAYMENT_SUCCESSFUL)) {
                        tryDequeue();
                        LOG.trace("Order " + qt.order.id + ": This messaging task already done, dequeue..");
                    } else if (qt.messageType == 1 && (!qt.order.messageSent.equals(MessageSent.NONE_SENT)
                            || !qt.order.paid.equals(PaymentStatus.TRYING))) {
                        tryDequeue();
                        LOG.trace("Order " + qt.order.id + ": This messaging task does not need to be done,"
                                + " dequeue..");
                    } else if (qt.messageType == 0) {
                        sendPaymentFailureMessage(qt.order);
                        LOG.debug("Order " + qt.order.id + ": Trying to connect to messaging service..");
                    } else if (qt.messageType == 1) {
                        sendPaymentPossibleErrorMsg(qt.order);
                        LOG.debug("Order " + qt.order.id + ": Trying to connect to messaging service..");
                    } else if (qt.messageType == 2) {
                        sendSuccessMessage(qt.order);
                        LOG.debug("Order " + qt.order.id + ": Trying to connect to messaging service..");
                    }
                } else if (qt.taskType.equals(TaskType.EMPLOYEE_DB)) {
                    if (qt.order.addedToEmployeeHandle) {
                        tryDequeue();
                        LOG.trace("Order " + qt.order.id + ": This employee handle task already done,"
                                + " dequeue..");
                    } else {
                        employeeHandleIssue(qt.order);
                        LOG.debug("Order " + qt.order.id + ": Trying to connect to employee handle..");
                    }
                }
            }
        }
        if (queueItems == 0) {
            LOG.trace("Queue is empty, returning..");
        } else {
            Thread.sleep(queueTaskTime / 3);
            tryDoingTasksInQueue();
        }
    }

}