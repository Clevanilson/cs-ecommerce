package com.github.clevanilson.cs_e_commerce.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.clevanilson.cs_e_commerce.application.port.TokenGenerator;

@Service 
public class JWTService implements TokenGenerator {
    private final String secret;
    private static final String ISSUER = "cs-ecommerce";

    public JWTService(@Value("${api.security.token.secret}") String secret) {
        this.secret = secret;
    }

    @Override
    public String generate(String username) {
        try {
            return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(username)
                .withExpiresAt(generateExpirationDate())
                .sign(Algorithm.HMAC256(secret));
        } catch(JWTCreationException exception) {
            throw new RuntimeException("Fail to create JWT token", exception);
        }
    }

    public String validate(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
        } catch(JWTVerificationException exception) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
    
}
