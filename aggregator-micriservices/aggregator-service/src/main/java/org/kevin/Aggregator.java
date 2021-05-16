package org.kevin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
public class Aggregator {

    @Resource
    private ProductInformationClient productInformationClient;

    @Resource
    private ProductInventoryClient productInventoryClient;

    @GetMapping("/product")
    public Product getProduct(){
        Product product = new Product();
        String productTitle = productInformationClient.getProductTitle();
        Integer inventories = productInventoryClient.getInventories();
        product.setTitle(productTitle);
        product.setProductInventories(Objects.requireNonNullElse(inventories, -1));
        return product;
    }
}
