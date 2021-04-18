package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Tobacco {

    public void smoke(Wizard wizard) {
        log.info("{} smoking {}", wizard.getClass().getSimpleName(), this.getClass().getSimpleName());
    }
}
