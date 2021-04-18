package org.kevin;

import java.io.Serial;

public class BookDuplicateException extends Exception{

    @Serial
    private static final long serialVersionUID = 5819623342115934090L;

    public BookDuplicateException(String message) {
        super(message);
    }
}
