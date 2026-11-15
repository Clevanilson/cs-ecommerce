package com.github.clevanilson.cs_e_commerce.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

class QuantityTest {
    @ParameterizedTest 
    @ValueSource(ints =  { 0, 1, 5000, 12343243, 312536, 654634, 6543, 6543 })
    void withValidValue(int value) {
        var sut = new Quantity(value);
        assertEquals(sut.getValue(), value);
    }

    @ParameterizedTest 
    @ValueSource(ints =  { -1, -5000, -12343243, -312536, -654634, -6543, -6543 })
    void withInvalidValue(int value) {
        var exception = assertThrows(
            DomainError.class,
            () -> new Quantity(value)
        );
        assertEquals(exception.getMessage(), "Quantity cannot be negative", exception.getMessage());
    }
}
