package org.kevin;

import java.io.Serial;

public class MaxNumOfEventsAllowedException extends Exception{

    @Serial
    private static final long serialVersionUID = -8989802905199894210L;

    public MaxNumOfEventsAllowedException(String message) {
        super(message);
    }
}
