package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Meteoroid extends GameObject {

    public Meteoroid(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    @Override
    public void collision(GameObject gameObject) {
        gameObject.collisionResolve(this);
    }

    @Override
    public void collisionResolve(FlamingAsteroid asteroid) {
        log.info(AppConstants.HITS, asteroid.getClass().getSimpleName(), this.getClass()
                .getSimpleName());
    }

    @Override
    public void collisionResolve(Meteoroid meteoroid) {
        log.info(AppConstants.HITS, meteoroid.getClass().getSimpleName(), this.getClass()
                .getSimpleName());
    }

    @Override
    public void collisionResolve(SpaceStationMir mir) {
        log.info(AppConstants.HITS, mir.getClass().getSimpleName(), this.getClass().getSimpleName());
    }

    @Override
    public void collisionResolve(SpaceStationIss iss) {
        log.info(AppConstants.HITS, iss.getClass().getSimpleName(), this.getClass().getSimpleName());
    }
}