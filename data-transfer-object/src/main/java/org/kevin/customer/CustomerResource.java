package org.kevin.customer;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomerResource {

    private final List<CustomerDto> customers;

    public List<CustomerDto> getAllCustomers(){
        return customers;
    }

    public void save(CustomerDto customerDto){
        customers.add(customerDto);
    }

    public void delete(String customerId) {
        customers.removeIf(customerDto -> customerDto.getId().equals(customerId));
    }
}
