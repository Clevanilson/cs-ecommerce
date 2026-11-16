package com.github.clevanilson.cs_e_commerce.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

class ClientTest {
    @Test 
    void withValidData() {
        var sut = new Client(1,"Test client");
        assertEquals(sut.getId(), 1);
        assertEquals(sut.getName(), "Test client");
    }

    @Test
    void withInvalidName() {
        var exception = assertThrows(
            DomainError.class,
             () -> Client.create("")
        );
        assertEquals(exception.getMessage(), "Name must contain at least 3 characters");
    }
}
