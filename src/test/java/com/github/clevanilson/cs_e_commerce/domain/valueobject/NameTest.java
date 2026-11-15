package com.github.clevanilson.cs_e_commerce.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

class NameTest {
    @ParameterizedTest   
    @CsvSource ({ 
        "'Verso Dessendre', 'Verso Dessendre'",
        "' Maelle', 'Maelle'",
        "'Ann ', 'Ann'",
        "'cYF7Ekcg8vxZ1tu1ujoKoGauOUvOZLzbeR4EvvhVvYFt5sTJfcLq1yRE74kIjZUV', 'cYF7Ekcg8vxZ1tu1ujoKoGauOUvOZLzbeR4EvvhVvYFt5sTJfcLq1yRE74kIjZUV'"
    })
    void withValidValue(String value, String expected) {
        var sut = new Name(value);
        assertEquals(sut.getValue(), expected);
    }

    @ParameterizedTest
    @CsvSource({
        "'ab', 'Name must contain at least 3 characters'",
        "'cYF7Ekcg8vxZ1tu1ujoKoGauOUvOZLzbeR4EvvhVvYFt5sTJfcLq1yRE74kIjZUVAa', 'Name must contain at most 64 characters'",
        "'ABCD❌', 'Name must contain only letters, spaces and numbers'"
    })
    void withInvalidValue(String value, String errorMessage) {
        var exception = assertThrows(DomainError.class, () -> new Name(value));
        assertEquals(exception.getMessage(), errorMessage);
    }
}
