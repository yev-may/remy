package com.yevay.remy.view.api.handler;

import com.yevay.remy.model.dto.error.CombinedErrorMassage;
import com.yevay.remy.model.dto.error.ErrorMessages;
import com.yevay.remy.model.dto.error.FieldErrorMessage;
import com.yevay.remy.model.dto.error.FieldErrorMessages;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        CombinedErrorMassage body = CombinedErrorMassage.builder()
                .messages(extractErrorMessages(errors))
                .fieldMessages(extractFieldErrorMessages(errors));
        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    private ErrorMessages extractErrorMessages(List<ObjectError> errors) {
        Set<String> messages = errors.stream()
                .filter(error -> !(error instanceof FieldError))
                .map(this::extractErrorMessage)
                .collect(Collectors.toSet());
        return ErrorMessages.builder()
                .messages(messages).build();
    }

    private FieldErrorMessages extractFieldErrorMessages(List<ObjectError> errors) {
        Set<FieldErrorMessage> messages = errors.stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .map(this::extractFieldErrorMessages)
                .collect(Collectors.toSet());
        return FieldErrorMessages.builder()
                .fieldErrors(messages).build();
    }

    private FieldErrorMessage extractFieldErrorMessages(FieldError error) {
        return FieldErrorMessage.builder()
                .field(error.getField())
                .message(error.getDefaultMessage()).build();
    }

    private String extractErrorMessage(ObjectError objectError) {
        return Optional.ofNullable(objectError.getDefaultMessage()).orElse("Unknown error");
    }
}
