package com.github.clevanilson.cs_e_commerce.domain.valueobject;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

public class Quantity {
    private int value;

    public Quantity(int value) {
        if (value < 0) throw new DomainError("Quantity cannot be negative");
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
