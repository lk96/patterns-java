package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("start pattern");
        var result = loop(10, 1).result();
        log.info("result {}", result);

    }

    /**
     * Manager for pattern. Define it with a factorial function.
     */
    public static Trampoline<Integer> loop(int times, int prod) {
        if (times == 0) {
            return Trampoline.done(prod);
        } else {
            return Trampoline.more(() -> loop(times - 1, prod * times));
        }
    }
}
