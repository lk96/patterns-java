package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {
        HeroStat heroStat = HeroStat.valueOf(10, 5, 0);
        HeroStat heroStat1 = HeroStat.valueOf(10, 5, 0);
        HeroStat heroStat2 = HeroStat.valueOf(5, 2, 8);
        log.info(heroStat.toString());

        log.info("Is heroStat and heroStat1 equal: {}", heroStat.equals(heroStat1));
        log.info("Is heroStat and heroStat2 equal: {}", heroStat.equals(heroStat2));
    }
}
