package com.remotelabs.hire.exceptions.handler;

import com.remotelabs.hire.exceptions.HireAuthException;
import com.remotelabs.hire.exceptions.HireBadRequestException;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.exceptions.models.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HireInternalException.class)
    public ResponseEntity<ApiError> handleHireInternalException(HireInternalException exception) {

        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HireBadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(HireBadRequestException exception) {

        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HireAuthException.class)
    public ResponseEntity<ApiError> handleBadRequestException(HireAuthException exception) {

        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage(exception.getMessage());
        apiError.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            message.append(errorMessage).append(";");
        });
        apiError.setMessage(message.toString());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}

