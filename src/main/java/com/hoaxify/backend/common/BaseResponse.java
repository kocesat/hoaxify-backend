package com.hoaxify.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BaseResponse {
    private boolean success;
    private String message;
    private Object data;

    public static BaseResponse success(Object data, String message) {
        return new BaseResponse(true, message, data);
    }

    public static BaseResponse fail(String message) {
        return new BaseResponse(false, message, null);
    }

}
