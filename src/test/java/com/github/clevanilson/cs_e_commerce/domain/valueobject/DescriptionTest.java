package com.github.clevanilson.cs_e_commerce.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;


class DescriptionTest {
    @ParameterizedTest
    @MethodSource("validValues")
    void withValidValue(String value) {
        var sut = new Description(value);
        assertEquals(sut.getValue(), value);
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    void withInvalidValue(String value, String errorMessages) {
        var exception = assertThrows(DomainError.class, () -> new Description(value));
        assertEquals(exception.getMessage(), errorMessages);
    }

    static Stream<Arguments> validValues() {
        return Stream.of(
            Arguments.of("Some description"),
            Arguments.of("Some"),
            Arguments.of("S".repeat(256))
        );
    }

    static Stream<Arguments> invalidValues() {
        return Stream.of(
            Arguments.of("Som", "Description must contain at least 4 characters"),
            Arguments.of("Some description".repeat(257), "Description must contain at most 256 characters")
        );
    }
}
