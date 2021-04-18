package org.kevin;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口
 */
public abstract class LetterComposite {

    private final List<LetterComposite> children = new ArrayList<>();

    public void add(LetterComposite letterComposite){
        children.add(letterComposite);
    }

    public int count(){
        return children.size();
    }

    protected void printThisBefore(){}

    protected void printThisAfter(){}

    public void print(){
        printThisBefore();
        children.forEach(LetterComposite::print);
        printThisAfter();
    }
}
