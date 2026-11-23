package com.github.clevanilson.cs_e_commerce.infra.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.github.clevanilson.cs_e_commerce.application.port.TokenGenerator;

class JWTServiceTest {
    private final JWTService jwtService = new JWTService("unit-test-secret");

    @Test
    void generatedTokenCanBeValidatedToItsUsername() {
        TokenGenerator tokenGenerator = jwtService;
        var token = tokenGenerator.generate("customer");

        assertEquals("customer", jwtService.validate(token));
    }

    @Test
    void invalidTokenReturnsEmptyUsername() {
        assertEquals("", jwtService.validate("not-a-token"));
    }
}
