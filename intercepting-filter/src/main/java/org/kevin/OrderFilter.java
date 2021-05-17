package org.kevin;

public class OrderFilter extends AbstractFilter {

    @Override
    public String execute(Order order) {
        String result = super.execute(order);
        String orderItem = order.getOrderItem();
        if (orderItem == null || orderItem.isEmpty()) {
            return result + "Invalid order! ";
        } else {
            return result;
        }
    }
}