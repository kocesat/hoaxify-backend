package com.hoaxify.backend.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiSuccess extends GenericResponse{
    private Object data;

    public ApiSuccess(int status, String message, String path) {
        super(true, message, status, path);
    }

    public ApiSuccess(int status, String message, String path, Object data) {
        super(true, message, status, path);
        this.setData(data);
    }
}
