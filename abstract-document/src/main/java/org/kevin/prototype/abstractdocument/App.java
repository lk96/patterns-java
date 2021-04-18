package org.kevin.prototype.abstractdocument;

import org.kevin.prototype.abstractdocument.domain.Car;
import org.kevin.prototype.abstractdocument.domain.enums.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("constructing parts and car");

        var wheel = Map.of(
                Property.TYPE.toString(), "wheel",
                Property.MODEL.toString(), "15C",
                Property.PRICE.toString(), 100L
        );

        var door = Map.of(
                Property.TYPE.toString(), "door",
                Property.MODEL.toString(), "lambo",
                Property.PRICE.toString(), 300L
        );

        var car = Map.of(
                Property.MODEL.toString(), "300SL",
                Property.PRICE.toString(), 10000L,
                Property.PARTS.toString(), List.of(wheel, door)
        );

        Car info = new Car(car);

        LOGGER.info("here is our car:");
        LOGGER.info("->model:{}", info.getModel().orElseThrow());
        LOGGER.info("->price:{}", info.getPrice().orElseThrow());
        LOGGER.info("->model: ");
        info.getParts().forEach(p ->
                LOGGER.info("\t{}/{}/{}",
                        p.getType().orElse(null),
                        p.getModel().orElse(null),
                        p.getPrice().orElse(null))
        );


    }
}
