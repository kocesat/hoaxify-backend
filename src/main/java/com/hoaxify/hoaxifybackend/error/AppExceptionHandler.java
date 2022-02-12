package com.hoaxify.hoaxifybackend.error;

import org.springframework.http.HttpStatus;
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
        ApiError error = new ApiError(400, "Validation Error", "/api/v1/users");
        List<FieldError> fieldErrors = exception.getFieldErrors();
        fieldErrors.stream()
                .forEach(fieldError -> {
                    validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
                });
        error.setValidationErrors(validationErrors);
        return error;
    }
}
