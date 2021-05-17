package org.kevin;

public class NameFilter extends AbstractFilter {

    @Override
    public String execute(Order order) {
        String result = super.execute(order);
        String name = order.getName();
        if (name == null || name.isEmpty() || name.matches(".*[^\\w|\\s]+.*")) {
            return result + "Invalid name! ";
        } else {
            return result;
        }
    }
}
