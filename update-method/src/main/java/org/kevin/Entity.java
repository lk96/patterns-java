package org.kevin;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 所有实体类的基类
 */
public abstract class Entity {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected int id;

    @Getter
    @Setter
    protected int position;

    public Entity(int id) {
        this.id = id;
        this.position = 0;
    }

    public abstract void update();
}
