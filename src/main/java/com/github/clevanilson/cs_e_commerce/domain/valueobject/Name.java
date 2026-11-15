package com.github.clevanilson.cs_e_commerce.domain.valueobject;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

public class Name {
    static byte minLength = 3;
    static byte maxLength = 64;
    private String value;

    public Name(String value) {
        if (value.length() < Name.minLength) {
            throw new DomainError(String.format("Name must contain at least %d characters", Name.minLength));
        }
        if (value.length() > Name.maxLength) {
            throw new DomainError(String.format("Name must contain at most %d characters", Name.maxLength));
        }
        if (!value.matches("^[A-Za-z0-9 ]+$")) {
            throw new DomainError("Name must contain only letters, spaces and numbers");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return  value;
    }
}

