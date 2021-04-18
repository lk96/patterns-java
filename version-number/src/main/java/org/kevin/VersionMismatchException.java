package org.kevin;

import java.io.Serial;

public class VersionMismatchException extends Exception{

    @Serial
    private static final long serialVersionUID = 8921989663001273791L;

    public VersionMismatchException(String message) {
        super(message);
    }
}
