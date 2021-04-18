package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.kevin.customer.CustomerDto;
import org.kevin.customer.CustomerResource;
import org.kevin.product.Product;
import org.kevin.product.ProductDto;
import org.kevin.product.ProductResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class App {

    public static void main(String[] args) {
        var customerOne = new CustomerDto("1", "Kelly", "Brown");
        var customerTwo = new CustomerDto("2", "Alfonso", "Bass");
        var customers = new ArrayList<>(List.of(customerOne, customerTwo));
        CustomerResource customerResource = new CustomerResource(customers);

        log.info("all Customers:-");
        List<CustomerDto> allCustomers = customerResource.getAllCustomers();
        printCustomerDetails(allCustomers);

        log.info("---------------------------------");
        log.info("deleting customer with id {1}");
        customerResource.delete(customerOne.getId());
        allCustomers = customerResource.getAllCustomers();
        printCustomerDetails(allCustomers);
        log.info("----------------------------------------------------------");

        log.info("Adding customer three}");
        var customerThree = new CustomerDto("3", "Lynda", "Blair");
        customerResource.save(customerThree);
        allCustomers = customerResource.getAllCustomers();
        printCustomerDetails(allCustomers);

        Product tv =
                new Product().setId(1L).setName("TV").setSupplier("Sony").setPrice(1000D).setCost(1090D);
        Product microwave =
                new Product()
                        .setId(2L)
                        .setName("microwave")
                        .setSupplier("Delonghi")
                        .setPrice(1000D)
                        .setCost(1090D);
        Product refrigerator =
                new Product()
                        .setId(3L)
                        .setName("refrigerator")
                        .setSupplier("Botsch")
                        .setPrice(1000D)
                        .setCost(1090D);
        Product airConditioner =
                new Product()
                        .setId(4L)
                        .setName("airConditioner")
                        .setSupplier("LG")
                        .setPrice(1000D)
                        .setCost(1090D);
        List<Product> products =
                new ArrayList<>(Arrays.asList(tv, microwave, refrigerator, airConditioner));
        ProductResource productResource = new ProductResource(products);

        log.info(
                "####### List of products including sensitive data just for admins: \n {}",
                Arrays.toString(productResource.getAllProductsForAdmin().toArray()));
        log.info(
                "####### List of products for customers: \n {}",
                Arrays.toString(productResource.getAllProductsForCustomer().toArray()));

        log.info("####### Going to save Sony PS5 ...");
        ProductDto.Request.Create createProductRequestDto =
                new ProductDto.Request.Create()
                        .setName("PS5")
                        .setCost(1000D)
                        .setPrice(1220D)
                        .setSupplier("Sony");
        productResource.save(createProductRequestDto);
        log.info(
                "####### List of products after adding PS5: {}",
                Arrays.toString(productResource.getProducts().toArray()));
    }

    private static void printCustomerDetails(List<CustomerDto> customerDtoList) {
        customerDtoList.forEach(customerDto -> log.info(customerDto.getFirstName()));
    }
}
