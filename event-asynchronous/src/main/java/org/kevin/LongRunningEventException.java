package org.kevin;

import java.io.Serial;

public class LongRunningEventException extends Exception{

    @Serial
    private static final long serialVersionUID = -6134815881834099851L;

    public LongRunningEventException(String message) {
        super(message);
    }
}
