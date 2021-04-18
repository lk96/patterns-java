package org.kevin.product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductResource {

    private final List<Product> products;

    public ProductResource(List<Product> products) {
        this.products = products;
    }

    public List<ProductDto.Response.Private> getAllProductsForAdmin() {
        return products.stream()
                .map(p -> new ProductDto.Response.Private().setId(p.getId()).setName(p.getName())
                        .setCost(p.getCost()).setPrice(p.getPrice())).collect(Collectors.toList());
    }

    public List<ProductDto.Response.Public> getAllProductsForCustomer(){
        return products.stream().map(p -> new ProductDto.Response.Public().setId(p.getId())
                .setName(p.getName()).setPrice(p.getPrice()).setId(p.getId()))
                .collect(Collectors.toList());
    }

    public void save(ProductDto.Request.Create productDto) {
        products.add(new Product().setId((long) (products.size() + 1))
                .setName(productDto.getName())
                .setPrice(productDto.getPrice())
                .setCost(productDto.getCost())
                .setSupplier(productDto.getSupplier()));
    }

    public List<Product> getProducts(){
        return products;
    }
}
