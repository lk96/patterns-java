package org.kevin.byteCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wizard {

    private static final Logger log = LoggerFactory.getLogger(Wizard.class);

    private int health;

    private int agility;

    private int wisdom;

    private int numberOfPlaySounds;

    private int numberOfSpawnedParticles;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void playSound(){
        log.info("Playing sound");
        numberOfPlaySounds++;
    }

    public void spawnParticles() {
        log.info("SpawnParticles");
        numberOfSpawnedParticles++;
    }

    public int getNumberOfPlaySounds() {
        return numberOfPlaySounds;
    }

    public int getNumberOfSpawnedParticles() {
        return numberOfSpawnedParticles;
    }
}
