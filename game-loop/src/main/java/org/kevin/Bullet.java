package org.kevin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bullet {

    private float position;

    public Bullet() {
        this.position = 0.0f;
    }

}
