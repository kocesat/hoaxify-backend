package com.hoaxify.backend.approval.exception;

import com.hoaxify.backend.common.HoaxifyException;

public class ApprovalException extends HoaxifyException {
    public ApprovalException() {
        super("Onay hatası");
    }

    public ApprovalException(String message) {
        super(message);
    }

    public ApprovalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApprovalException(Throwable cause) {
        super(cause);
    }

    public ApprovalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
