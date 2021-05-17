package org.kevin;

public class ContactFilter extends AbstractFilter {

    @Override
    public String execute(Order order) {
        String result = super.execute(order);
        String contactNumber = order.getContactNumber();
        if (contactNumber == null || contactNumber.isEmpty()
                || contactNumber.matches(".*[^\\d]+.*")
                || contactNumber.length() != 11) {
            return result + " Invalid concat number!";
        } else {
            return result;
        }
    }
}
