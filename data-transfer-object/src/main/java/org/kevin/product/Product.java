package org.kevin.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public final class Product {

    private Long id;

    private String name;

    private Double price;

    private Double cost;

    private String supplier;

    public Product(Long id, String name, Double price, Double cost, String supplier) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.supplier = supplier;
    }

    public Product() {
    }


}
