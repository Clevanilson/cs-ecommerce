package com.github.clevanilson.cs_e_commerce.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

class PriceTest {
    @ParameterizedTest   
    @ValueSource(doubles = { 30.32f, 23.54f, 4132.23f })
    void withValidValue(double value) {
        var sut = new Price(value);
        assertEquals(sut.getValue(), value);
    }

    @ParameterizedTest   
    @ValueSource(doubles = { -30.32f, -23.54f, -4132.23f, 0 })
    void withInvalidValue(double value) {
        var exception = assertThrows(DomainError.class, () -> new Price(value));
        assertEquals(exception.getMessage(), "Price cannot be less than 1");
    }
}
