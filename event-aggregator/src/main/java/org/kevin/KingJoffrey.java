package org.kevin;

import lombok.extern.slf4j.Slf4j;

/**
 * KingJoffrey 观察事件从{@link KingsHand}.
 */
@Slf4j
public class KingJoffrey implements EventObserver {
    @Override
    public void onEvent(Event event) {
        log.info("Received event form the king's hand:{}", event.toString());
    }
}
