package org.kevin;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Target {

    private Size size;

    private Visibility visibility;

    public void printStatus() {
        log.info("{},[size={}] [visibility={}]", this, getSize(), getVisibility());
    }

    public void changeSize(){
        Size size = getSize() == Size.NORMAL ? Size.SMALL : Size.NORMAL;
        setSize(size);
    }

    public void changeVisibility(){
        Visibility visibility = getVisibility() == Visibility.INVISIBLE ? Visibility.VISIBLE : Visibility.INVISIBLE;
        setVisibility(visibility);
    }
}
