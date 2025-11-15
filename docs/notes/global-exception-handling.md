
---

# 🚨 **Global Exception Handling – Notes**

This document explains how **Global Exception Handling** is implemented in the EMS project using **Spring Boot**, ensuring consistent and readable API error responses.

---

# 🎯 **Purpose of Global Exception Handling**

Global Exception Handling helps to:

* Centralize all exception responses
* Avoid repeating `try/catch` blocks
* Return consistent JSON error formats
* Improve debugging with structured logs
* Make the API more predictable and user-friendly

---

# 📁 **Package Structure**

```
com.algowebpro.common.exception
│
├── GlobalExceptionHandler.java
└── ResourceNotFoundException.java
```

```
com.algowebpro.common.response
│
└── ErrorResponse.java
```

---

# 🧩 **1. Custom Error Response Structure**

### 📁 `com.algowebpro.common.response.ErrorResponse`

```java
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private String errorCode;
}
```

### 📌 Explanation:

* `timestamp` → When the error occurred
* `message` → Human-readable error message
* `details` → API path
* `errorCode` → Developer-friendly internal error type

---

# 🧩 **2. Custom Exception Class**

### 📁 `com.algowebpro.common.exception.ResourceNotFoundException`

```java
package com.algowebpro.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException() {
        super("Resource Not Found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

### 📌 Usage:

Throw this exception when a record is not found in DB.

---

# 🧩 **3. Global Exception Handler**

### 📁 `com.algowebpro.common.exception.GlobalExceptionHandler`

```java
package com.algowebpro.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.algowebpro.common.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Handle Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {

        log.error("ResourceNotFoundException: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .errorCode("RESOURCE_NOT_FOUND")
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Handle all Runtime Exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {

        log.error("RuntimeException: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .errorCode("RUNTIME_EXCEPTION")
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle all other Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {

        log.error("Exception: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("An unexpected error occurred")
                .details(request.getDescription(false))
                .errorCode("UNKNOWN_ERROR")
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

---

# 🧪 **4. Example Error Response**

### When employee ID not found:

```
GET /api/v1/employees/999
```

### Response:

```json
{
  "timestamp": "2025-11-15T12:23:45.123",
  "message": "Employee not found with id: 999",
  "details": "uri=/api/v1/employees/999",
  "errorCode": "RESOURCE_NOT_FOUND"
}
```

---

# 🧠 **5. Best Practices**

### ✔ Keep error responses consistent

Never return raw exceptions.

### ✔ Always log exceptions

Use `@Slf4j` and `log.error()` inside the handler.

### ✔ Throw custom exceptions for business rules

Examples:

* `InvalidDataException`
* `UnauthorizedException`
* `DuplicateRecordException`

### ✔ Avoid exposing sensitive details

E.g., database errors, stack traces.

### ✔ Use proper HTTP status codes

| Type                 | Status |
| -------------------- | ------ |
| Resource Not Found   | `404`  |
| Validation Error     | `400`  |
| Authentication Error | `401`  |
| Authorization Error  | `403`  |
| Server Error         | `500`  |

---

# 🎉 Summary

You now have:

✔ Custom Exception
✔ Global Exception Handler
✔ Standardized ErrorResponse
✔ Clean logs
✔ Predictable API behavior

---

