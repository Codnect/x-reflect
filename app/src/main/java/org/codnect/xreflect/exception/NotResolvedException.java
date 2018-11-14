package org.codnect.xreflect.exception;

public class NotResolvedException extends RuntimeException {

    public NotResolvedException(String message) {
        super(message);
    }

    public NotResolvedException(Throwable cause) {
        super(cause);
    }

    public NotResolvedException(String message, Throwable cause) {
        super(message, cause);
    }

}
