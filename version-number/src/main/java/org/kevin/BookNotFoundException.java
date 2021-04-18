package org.kevin;

import java.io.Serial;

public class BookNotFoundException extends Exception{

    @Serial
    private static final long serialVersionUID = -8693211162061752378L;

    public BookNotFoundException(String message) {
        super(message);
    }
}
