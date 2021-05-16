package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ImageController {

    @GetMapping("/imagePath")
    public String getImagePath(){
        log.info("find image");
        return "image.png";
    }

}
