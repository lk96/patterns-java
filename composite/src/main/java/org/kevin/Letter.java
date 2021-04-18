package org.kevin;

import lombok.RequiredArgsConstructor;

/**
 * 字母
 */
@RequiredArgsConstructor
public class Letter extends LetterComposite{

    private final char character;

    @Override
    protected void printThisBefore() {
        System.out.print(character);
    }
}
