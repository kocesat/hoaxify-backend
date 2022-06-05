package com.hoaxify.backend.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError extends GenericResponse {
    private Map<String, String> validationErrors;

    public ApiError(int status, String message, String path) {
        super(false, message, status, path);
    }
}
