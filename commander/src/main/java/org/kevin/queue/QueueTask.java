package org.kevin.queue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.kevin.Order;

@RequiredArgsConstructor
public class QueueTask {

    public enum TaskType {
        MESSAGING,
        PAYMENT,
        EMPLOYEE_DB
    }

    public final Order order;
    public final TaskType taskType;
    public final int messageType; //0-fail, 1-error, 2-success

    /*we could have varargs Object instead to pass in any parameter instead of just message type
    but keeping it simple here*/
    @Getter
    @Setter
    private long firstAttemptTime = -1L; //when first time attempt made to do task

    public String getType() {
        if (!this.taskType.equals(TaskType.MESSAGING)) {
            return this.taskType.toString();
        } else {
            if (this.messageType == 0) {
                return "Payment Failure Message";
            } else if (this.messageType == 1) {
                return "Payment Error Message";
            } else {
                return "Payment Success Message";
            }
        }
    }
}
