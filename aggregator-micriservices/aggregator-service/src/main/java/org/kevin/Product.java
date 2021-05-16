package org.kevin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {

    /**
     * 名称
     */
    private String title;

    /**
     * 库存
     */
    private int productInventories;
}
