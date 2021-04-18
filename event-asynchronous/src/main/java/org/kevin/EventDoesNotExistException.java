package org.kevin;

import java.io.Serial;

public class EventDoesNotExistException extends Exception{
    @Serial
    private static final long serialVersionUID = 5426738828148483414L;

    public EventDoesNotExistException(String message) {
        super(message);
    }
}
