package com.github.clevanilson.cs_e_commerce.infra.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.github.clevanilson.cs_e_commerce.application.port.CredentialAuthenticator;

@Component
public class SpringCredentialAuthenticator implements CredentialAuthenticator {
    private final AuthenticationManager authenticationManager;

    public SpringCredentialAuthenticator(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String authenticate(String username, String password) {
        var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        return authentication.getName();
    }
}
