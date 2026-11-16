package com.github.clevanilson.cs_e_commerce.domain.error;

public class ApplicationError extends IllegalArgumentException {
    public ApplicationError(String message) {
        super(message);
    }
}
