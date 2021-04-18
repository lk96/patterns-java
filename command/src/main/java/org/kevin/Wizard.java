package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;

@Slf4j
public class Wizard {

    private final Deque<Runnable> undoStack = new LinkedList<>();
    private final Deque<Runnable> redoStack = new LinkedList<>();

    public Wizard() {
    }

    public void castSpell(Runnable runnable) {
        runnable.run();
        undoStack.offerLast(runnable);
    }

    public void undoLastSpell(){
        if (!undoStack.isEmpty()) {
            Runnable runnable = undoStack.pollLast();
            redoStack.offerLast(runnable);
            runnable.run();
        }
    }

    public void redoLastSpell(){
        if (!redoStack.isEmpty()) {
            Runnable runnable = redoStack.pollLast();
            undoStack.offerLast(runnable);
            runnable.run();
        }
    }

    @Override
    public String toString() {
        return "Wizard";
    }
}
