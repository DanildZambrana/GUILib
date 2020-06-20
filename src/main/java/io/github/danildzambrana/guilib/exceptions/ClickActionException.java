package io.github.danildzambrana.guilib.exceptions;

public class ClickActionException extends Exception {
    public ClickActionException() {
    }

    public ClickActionException(String message) {
        super(message);
    }

    public ClickActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClickActionException(Throwable cause) {
        super(cause);
    }

    public ClickActionException(String message,
                                Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
