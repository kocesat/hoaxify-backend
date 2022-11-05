package com.hoaxify.backend.common;

public class HoaxifyException extends Exception{
    public HoaxifyException() {
    }

    public HoaxifyException(String message) {
        super(message);
    }

    public HoaxifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public HoaxifyException(Throwable cause) {
        super(cause);
    }

    public HoaxifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
