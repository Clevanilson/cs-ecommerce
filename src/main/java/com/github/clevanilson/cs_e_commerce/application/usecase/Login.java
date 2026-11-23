package com.github.clevanilson.cs_e_commerce.application.usecase;

import org.springframework.stereotype.Service;

import com.github.clevanilson.cs_e_commerce.application.port.CredentialAuthenticator;
import com.github.clevanilson.cs_e_commerce.application.port.TokenGenerator;
import com.github.clevanilson.cs_e_commerce.domain.dto.LoginInput;

@Service
public class Login {
    private final CredentialAuthenticator credentialAuthenticator;
    private final TokenGenerator tokenGenerator;

    public Login(CredentialAuthenticator credentialAuthenticator, TokenGenerator tokenGenerator) {
        this.credentialAuthenticator = credentialAuthenticator;
        this.tokenGenerator = tokenGenerator;
    }

    public String execute(LoginInput input) {
        var username = credentialAuthenticator.authenticate(input.login(), input.password());
        return tokenGenerator.generate(username);
    }
}
