package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PriceController {

    @GetMapping("/price")
    public String getPrice(){
      log.info("find price");
        return "30";
    }
}
