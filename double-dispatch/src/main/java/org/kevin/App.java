package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class App {

    public static void main(String[] args) {
        // initialize game objects and print their status
        var objects = List.of(
                new FlamingAsteroid(0, 0, 5, 5),
                new SpaceStationMir(1, 1, 2, 2),
                new Meteoroid(10, 10, 15, 15),
                new SpaceStationIss(12, 12, 14, 14)
        );
        objects.forEach(o -> log.info(o.toString()));
        log.info("");

        // collision check
        objects.forEach(o1 -> objects.forEach(o2 -> {
            if (o1 != o2 && o1.intersectsWith(o2)) {
                o1.collision(o2);
            }
        }));
        log.info("");

        // output eventual object statuses
        objects.forEach(o -> log.info(o.toString()));
        log.info("");
    }
}
