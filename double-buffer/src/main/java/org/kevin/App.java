package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

@Slf4j
public class App {

    public static void main(String[] args) {
        final Scene scene = new Scene();
        List<MutablePair<Integer, Integer>> mutablePairs = List.of(
                new MutablePair<>(1, 1),
                new MutablePair<>(5, 6),
                new MutablePair<>(3, 2)
        );
        scene.draw(mutablePairs);
        Buffer buffer = scene.getBuffer();
        printBlackPixelCoordinate(buffer);

        List<MutablePair<Integer, Integer>> mutablePairs1 = List.of(
                new MutablePair<>(3, 7),
                new MutablePair<>(6, 1)
        );
        scene.draw(mutablePairs1);
        Buffer buffer1 = scene.getBuffer();
        printBlackPixelCoordinate(buffer1);
    }

    public static void printBlackPixelCoordinate(Buffer buffer) {
        StringBuilder builder = new StringBuilder("Black Pixels:");
        Pixel[] pixel = buffer.getPixel();
        for (int i = 0; i < pixel.length; i++) {
            if (pixel[i] == Pixel.BLACK) {
                int y = i / FrameBuffer.WIDTH;
                int x = i % FrameBuffer.WIDTH;
                builder.append("(").append(x).append(", ").append(y).append(")");
            }
        }
        log.info(builder.toString());
    }
}
