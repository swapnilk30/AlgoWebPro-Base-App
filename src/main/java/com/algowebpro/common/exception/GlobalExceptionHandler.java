package com.algowebpro.common.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

		ErrorResponse error = ErrorResponse.builder().message(ex.getMessage()).details(request.getDescription(false))
				.errorCode("INTERNAL_SERVER_ERROR").build();

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Runtime Exception Handler
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex, WebRequest request) {

		log.error("Runtime Exception: {}", ex.getMessage());

		ErrorResponse error = ErrorResponse.builder().message(ex.getMessage()).details(request.getDescription(false))
				.errorCode("RUNTIME_EXCEPTION").build();

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handle Resource Not Found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {

		log.error("Resource Not Found: {}", ex.getMessage());

		ErrorResponse error = ErrorResponse.builder().message(ex.getMessage()).details(request.getDescription(false))
				.errorCode("RESOURCE_NOT_FOUND").build();
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// Handle Field validations
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

		log.error("Validation error occurred: {}", ex.getMessage());

		Map<String, String> fieldErrors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			if (error instanceof FieldError fieldError) {
				String fieldName = fieldError.getField();
				String errorMessage = error.getDefaultMessage();
				log.warn("Validation failed for field '{}': {}", fieldName, errorMessage);
				fieldErrors.put(fieldName, errorMessage);
			} else {
				// In case of ObjectError
				log.warn("Validation error (non-field): {}", error.getDefaultMessage());
				fieldErrors.put("objectError", error.getDefaultMessage());
			}
		});

		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("message", "Validation failed");
		response.put("errors", fieldErrors);
		response.put("errorCode", "VALIDATION_ERROR");

		log.info("Returning {} validation errors to client.", fieldErrors.size());

		return ResponseEntity.badRequest().body(response);
	}

	/**
	 * Handle bad request
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
		log.error("Bad request: {}", ex.getMessage());

		ErrorResponse errorResponse = ErrorResponse.builder().message(ex.getMessage()).details(request.getDescription(false))
				.errorCode("BAD_REQUEST").build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
