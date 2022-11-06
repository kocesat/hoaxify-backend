package com.hoaxify.backend.webclient.exception;

import com.hoaxify.backend.common.HoaxifyException;

public class TodoNotFoundEx extends HoaxifyException {
    public TodoNotFoundEx() {
        super("Todo bulunamadı");
    }

    public TodoNotFoundEx(String message) {
        super(message);
    }

    public TodoNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }

    public TodoNotFoundEx(Throwable cause) {
        super(cause);
    }

    public TodoNotFoundEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
