package com.github.clevanilson.cs_e_commerce.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.clevanilson.cs_e_commerce.domain.dto.ErrorResponse;
import com.github.clevanilson.cs_e_commerce.domain.error.ApplicationError;
import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;
import com.github.clevanilson.cs_e_commerce.domain.error.NotFoundError;

@RestControllerAdvice 
public class GlobalErrorHandler {
    @ExceptionHandler(DomainError.class)
    public ResponseEntity<ErrorResponse> domainError(DomainError error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ErrorResponse(error.getMessage())
        );
    }

    @ExceptionHandler(ApplicationError.class)
    public ResponseEntity<ErrorResponse> applicationError(ApplicationError error) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(
            new ErrorResponse(error.getMessage())
        );
    }

    @ExceptionHandler(NotFoundError.class)
    public ResponseEntity<ErrorResponse> applicationError(NotFoundError error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(error.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorResponse> unexpectedError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new ErrorResponse("Unexpected error, try again later")
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public  ResponseEntity<ErrorResponse> authenticationError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            new ErrorResponse("Invalid username or password")
        );
    }
    
}
