package org.kevin;

public interface Buffer {

    void clear(int x, int y);

    void draw(int x, int y);

    void clearAll();

    Pixel[] getPixel();
}
