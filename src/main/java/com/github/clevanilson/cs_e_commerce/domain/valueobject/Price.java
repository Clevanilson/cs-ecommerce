package com.github.clevanilson.cs_e_commerce.domain.valueobject;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

public class Price {
    private double value;

    public Price(double value) {
        if (value < 1) {
            throw new DomainError("Price cannot be less than 1");
        }
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
