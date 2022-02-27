package com.hoaxify.hoaxifybackend.common;

import lombok.Data;

@Data
public class ApiSuccess extends GenericResponse{
    private Object data;
    public ApiSuccess(int status, String message, String path) {
        super(true, message, status, path);
    }
}
