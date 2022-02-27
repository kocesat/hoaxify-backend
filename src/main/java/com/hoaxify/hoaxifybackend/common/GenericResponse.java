package com.hoaxify.hoaxifybackend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    private boolean success = true;
    private String message = "";
    private int status;
    private String path;
    private long timeStamp = new Date().getTime();

    public GenericResponse(boolean success, String message, int status, String path) {
        this.success = success;
        this.message = message;
        this.status = status;
        this.path = path;
    }
}
