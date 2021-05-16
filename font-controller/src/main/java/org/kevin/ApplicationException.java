package org.kevin;

import java.io.Serial;

public class ApplicationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6551459775472921090L;

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
