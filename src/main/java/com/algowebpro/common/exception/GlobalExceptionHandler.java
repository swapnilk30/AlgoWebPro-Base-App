package com.algowebpro.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.algowebpro.common.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	
	// Generic Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex, WebRequest request) {

        log.error("Unhandled Exception: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .errorCode("INTERNAL_SERVER_ERROR")
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
    // Runtime Exception Handler
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex, WebRequest request) {

        log.error("Runtime Exception: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .errorCode("RUNTIME_EXCEPTION")
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	// Handle Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {

        log.error("Resource Not Found: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .errorCode("RESOURCE_NOT_FOUND")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
