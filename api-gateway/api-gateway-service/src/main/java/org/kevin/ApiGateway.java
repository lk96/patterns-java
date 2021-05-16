package org.kevin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ApiGateway {

    @Resource
    private ImageClient imageClient;

    @Resource
    private PriceClient priceClient;

    @GetMapping("desktop")
    public DesktopProduct getProductDesktop(){
        DesktopProduct product = new DesktopProduct();
        product.setImagePath(imageClient.getImagePath());
        product.setPrice(priceClient.getPrice());
        return product;
    }

    @GetMapping("mobile")
    public MobileProduct getProductMobile(){
        MobileProduct product = new MobileProduct();
        product.setPrice(priceClient.getPrice());
        return product;
    }

}
