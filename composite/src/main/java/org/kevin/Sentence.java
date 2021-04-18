package org.kevin;

import java.util.List;

/**
 * 句子
 */
public class Sentence extends LetterComposite{

    public Sentence(List<Word> words) {
        words.forEach(this::add);
    }

    @Override
    protected void printThisAfter() {
        System.out.print(".");
    }
}
