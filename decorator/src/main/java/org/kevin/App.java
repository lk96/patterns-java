package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("A simple looking troll approaches");
        SimpleTroll simpleTroll = new SimpleTroll();
        simpleTroll.attack();
        simpleTroll.fleeBattle();
        log.info("Simple troll power {}.\n", simpleTroll.getAttackPower());

        log.info("A troll with huge club surprises you.");
        ClubbedTroll clubbedTroll = new ClubbedTroll(simpleTroll);
        clubbedTroll.attack();
        clubbedTroll.fleeBattle();
        log.info("Clubbed troll power {}.\n", clubbedTroll.getAttackPower());
    }
}
