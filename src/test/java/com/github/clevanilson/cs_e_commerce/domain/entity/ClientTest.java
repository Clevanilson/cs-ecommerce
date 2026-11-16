package com.github.clevanilson.cs_e_commerce.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;
import com.github.clevanilson.cs_e_commerce.support.ClientSupport;

class ClientTest {
    private ClientSupport clientSupport = new ClientSupport();

    @BeforeEach
    void setup() {
        clientSupport.setup();
    }

    @Test 
    void withValidData() {
        var sut = clientSupport.create();
        assertEquals(sut.getId(), clientSupport.id);
        assertEquals(sut.getName(), clientSupport.name);
    }

    @Test
    void withInvalidName() {
        clientSupport.name = "";
        var exception = assertThrows(
            DomainError.class,
             () -> clientSupport.create()
        );
        assertEquals(exception.getMessage(), "Name must contain at least 3 characters");
    }
}
