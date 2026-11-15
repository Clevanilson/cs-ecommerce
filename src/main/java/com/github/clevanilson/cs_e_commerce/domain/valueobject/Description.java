package com.github.clevanilson.cs_e_commerce.domain.valueobject;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

public class Description {
    static byte minLength = 4;
    static short maxLength = 256;
    private String value;

    public Description(String value) {
        if (value.length() < Description.minLength) {
            throw new DomainError(
                String.format("Description must contain at least %d characters", Description.minLength)
            );
        }
        if (value.length() > Description.maxLength) {
            throw new DomainError(
                String.format("Description must contain at most %d characters", Description.maxLength)
            );
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
