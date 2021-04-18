package org.kevin;

import java.io.Serial;

public class InvalidOperationException extends Exception{

    @Serial
    private static final long serialVersionUID = 4419228611945457356L;

    public InvalidOperationException(String message) {
        super(message);
    }
}
