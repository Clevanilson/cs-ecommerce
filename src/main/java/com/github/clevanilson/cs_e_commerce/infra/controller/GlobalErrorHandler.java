package com.github.clevanilson.cs_e_commerce.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.clevanilson.cs_e_commerce.domain.dto.ErrorResponse;
import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

@RestControllerAdvice 
public class GlobalErrorHandler {
    @ExceptionHandler(DomainError.class)
    public ResponseEntity<ErrorResponse> domainError(DomainError error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ErrorResponse(error.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorResponse> unexpectedError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new ErrorResponse("Unexpected error, try again later")
        );
    }
    
}
