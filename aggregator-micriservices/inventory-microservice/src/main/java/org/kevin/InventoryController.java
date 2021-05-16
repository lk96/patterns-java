package org.kevin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @GetMapping("inventories")
    public int getProductInventories(){
        return 10;
    }
}
