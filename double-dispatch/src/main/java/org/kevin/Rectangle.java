package org.kevin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Rectangle {

    private final int left;

    private final int top;

    private final int right;

    private final int bottom;

    boolean intersectsWith(Rectangle rectangle) {
        return !(rectangle.getLeft() > getRight() || rectangle.getRight() < getLeft()
                || rectangle.getTop() > getBottom() || rectangle.getBottom() < getTop());
    }

    @Override
    public String toString() {
        return String.format("[%d,%d,%d,%d]", getLeft(), getTop(), getRight(), getBottom());
    }
}
