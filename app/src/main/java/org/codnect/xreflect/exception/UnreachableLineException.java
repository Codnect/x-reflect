package org.codnect.xreflect.exception;

/**
 * Created by Burak Koken on 14.11.2018.
 *
 * @author Burak Koken
 */
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
