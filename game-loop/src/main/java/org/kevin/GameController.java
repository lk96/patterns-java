package org.kevin;

public class GameController {

    protected final Bullet bullet;

    public GameController() {
        bullet = new Bullet();
    }

    public void moveBullet(float offset) {
        float position = bullet.getPosition();
        bullet.setPosition(position + offset);
    }

    public float getBulletPosition() {
        return bullet.getPosition();
    }
}
