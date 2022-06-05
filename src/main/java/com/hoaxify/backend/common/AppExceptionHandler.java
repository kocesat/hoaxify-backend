package com.hoaxify.backend.common;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();
        ApiError error = new ApiError(400,
                AppConstants.VALIDATION_ERROR_MESSAGE, AppConstants.USERCONTROLLER_BASE_PATH);
        List<FieldError> fieldErrors = exception.getFieldErrors();
        fieldErrors.stream()
                .forEach(fieldError -> {
                    validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
                });
        error.setValidationErrors(validationErrors);
        return error;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleAuthError(AuthenticationException exception) {
        ApiError error = new ApiError(401,
                AppConstants.UNAUTHORIZED_MESSAGE, AppConstants.AUTHCONTROLLER_BASE_PATH);
        return error;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleGeneralException(Exception exception) {
        return new ApiError(500,
                AppConstants.INTERNAL_SERVER_ERROR_MESSAGE, "/api/*");
    }
}
