package com.github.clevanilson.cs_e_commerce.domain.error;

public class DomainError extends IllegalArgumentException {
    public DomainError(String message) {
        super(message);
    }
}
