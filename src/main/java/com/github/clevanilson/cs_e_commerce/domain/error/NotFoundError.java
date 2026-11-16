package com.github.clevanilson.cs_e_commerce.domain.error;

public class NotFoundError extends IllegalArgumentException {
    public NotFoundError(String message) {
        super(message);
    }
}
