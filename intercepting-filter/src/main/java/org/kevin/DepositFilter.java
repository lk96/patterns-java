package org.kevin;

public class DepositFilter extends AbstractFilter{

    @Override
    public String execute(Order order) {
        String result = super.execute(order);
        String depositNumber = order.getDepositNumber();
        if (depositNumber == null || depositNumber.isEmpty()) {
            return result + " Invalid deposit number!";
        }else {
            return result;
        }
    }
}
