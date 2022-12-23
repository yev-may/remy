package com.yevay.remy.view.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String FIELD_OBJECT_ERRORS = "objectErrors";
    private static final String FIELD_FIELD_ERRORS = "fieldErrors";
    private static final String FIELD_FIELD = "field";
    private static final String FIELD_MESSAGE = "message";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        Map<String, Object> body = Map.of(
                FIELD_OBJECT_ERRORS, handleObjectErrors(errors),
                FIELD_FIELD_ERRORS, handleFieldErrors(errors));
        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    private Set<String> handleObjectErrors(List<ObjectError> errors) {
        return errors.stream()
                .filter(error -> !(error instanceof FieldError))
                .map(this::extractErrorMessage)
                .collect(Collectors.toSet());
    }

    private Set<Map<String, String>> handleFieldErrors(List<ObjectError> errors) {
        return errors.stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .map(this::toErrorMap)
                .collect(Collectors.toSet());
    }

    private Map<String, String> toErrorMap(FieldError error) {
        return Map.of(
                FIELD_FIELD, error.getField(),
                FIELD_MESSAGE, extractErrorMessage(error));
    }

    private String extractErrorMessage(ObjectError objectError) {
        return Optional.ofNullable(objectError.getDefaultMessage()).orElse("Unknown error");
    }
}
