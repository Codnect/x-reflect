package org.codnect.xreflect.exception;

/**
 * Created by Burak Koken on 14.11.2018.
 *
 * @author Burak Koken
 */
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
