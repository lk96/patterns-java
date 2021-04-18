package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Slf4j
public class Scene {

    private final Buffer[] frameBuffers;

    private int current;

    private int next;


    public Scene() {
        frameBuffers = new FrameBuffer[2];
        frameBuffers[0] = new FrameBuffer();
        frameBuffers[1] = new FrameBuffer();
        current = 0;
        next = 1;
    }

    public void draw(List<? extends Pair<Integer,Integer>> coordinateList){
        log.info("start drawing next frame");
        log.info("current buffer:" + current + "next buffer" + next);
        frameBuffers[next].clearAll();
        coordinateList.forEach(coordinate->{
            Integer key = coordinate.getKey();
            Integer value = coordinate.getValue();
            frameBuffers[next].draw(key, value);
        });
    }

    public Buffer getBuffer() {
        log.info("get current buffer:" + current);
        return frameBuffers[current];
    }

    private void swap(){
        current = current ^ next;
        next = current ^ next;
        current = current ^ next;
    }
}
