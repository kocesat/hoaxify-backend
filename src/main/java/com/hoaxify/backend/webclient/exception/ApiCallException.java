package com.hoaxify.backend.webclient.exception;

import com.hoaxify.backend.common.HoaxifyException;

public class ApiCallException extends HoaxifyException {
    public ApiCallException() {
        super("Servis çağrısı sırasında bir hata oluştu");
    }

    public ApiCallException(String message) {
        super(message);
    }

    public ApiCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiCallException(Throwable cause) {
        super(cause);
    }

    public ApiCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
