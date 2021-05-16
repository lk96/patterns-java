package org.kevin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {

    @GetMapping("/information")
    public String getProductTitle(){
        return "Product Title";
    }
}
