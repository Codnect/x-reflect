package org.codnect.xreflect.exception;

public class UnreachableLineException extends RuntimeException {

    public UnreachableLineException(String message) {
        super(message);
    }

    public UnreachableLineException(Throwable cause) {
        super(cause);
    }

    public UnreachableLineException(String message, Throwable cause) {
        super(message, cause);
    }

}
