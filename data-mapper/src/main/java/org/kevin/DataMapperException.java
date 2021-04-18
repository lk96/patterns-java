package org.kevin;

import java.io.Serial;

public class DataMapperException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -840949794358021720L;

    public DataMapperException(final String message) {
        super(message);
    }
}
