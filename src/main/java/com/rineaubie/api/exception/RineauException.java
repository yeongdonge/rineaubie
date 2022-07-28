package com.rineaubie.api.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class RineauException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public RineauException(String message) {
        super(message);
    }

    public RineauException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }

    public void addValidation(FieldError fieldError) {
        validation.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
