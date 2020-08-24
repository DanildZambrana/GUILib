/*
 * Copyright (c) 2020. This file is subject to the terms and conditions defined in file 'LICENSE.md', which is part of this source code package.
 */

package io.github.danildzambrana.guilib.exceptions;

public class MinOrMaxNumberException extends MenuException{
    public MinOrMaxNumberException() {
    }

    public MinOrMaxNumberException(String message) {
        super(message);
    }

    public MinOrMaxNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinOrMaxNumberException(Throwable cause) {
        super(cause);
    }

    public MinOrMaxNumberException(String message,
                                   Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
