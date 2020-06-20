package io.github.danildzambrana.guilib.exceptions;

public class ButtonNotAllowedHereException extends Exception {
    public ButtonNotAllowedHereException() {
    }

    public ButtonNotAllowedHereException(String message) {
        super(message);
    }

    public ButtonNotAllowedHereException(String message, Throwable cause) {
        super(message, cause);
    }

    public ButtonNotAllowedHereException(Throwable cause) {
        super(cause);
    }

    public ButtonNotAllowedHereException(String message,
                                         Throwable cause,
                                         boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
