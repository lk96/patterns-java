package org.kevin;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class App {

    public static void main(String[] args) {
        KingJoffrey kingJoffrey = new KingJoffrey();
        KingsHand kingsHand = new KingsHand(kingJoffrey);
        List<EventEmitter> eventEmitters = List.of(
                kingsHand,
                new LordBaelish(kingsHand),
                new LordVary(kingsHand),
                new Scout(kingsHand)
        );
        Arrays.stream(Weekday.values())
                .<Consumer<? super EventEmitter>>map(weekday -> eventEmitter -> eventEmitter.timePasses(weekday))
                .forEachOrdered(eventEmitters::forEach);
    }
}
