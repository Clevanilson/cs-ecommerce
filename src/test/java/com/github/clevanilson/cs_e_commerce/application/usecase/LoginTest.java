package com.github.clevanilson.cs_e_commerce.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.github.clevanilson.cs_e_commerce.application.port.CredentialAuthenticator;
import com.github.clevanilson.cs_e_commerce.application.port.TokenGenerator;
import com.github.clevanilson.cs_e_commerce.domain.dto.LoginInput;

class LoginTest {
    private final CredentialAuthenticator credentialAuthenticator = mock(CredentialAuthenticator.class);
    private final TokenGenerator tokenGenerator = mock(TokenGenerator.class);
    private final Login login = new Login(credentialAuthenticator, tokenGenerator);

    @Test
    void authenticatesCredentialsBeforeGeneratingToken() {
        when(credentialAuthenticator.authenticate("customer", "password")).thenReturn("customer");
        when(tokenGenerator.generate("customer")).thenReturn("jwt-token");

        var token = login.execute(new LoginInput("customer", "password"));

        assertEquals("jwt-token", token);
        verify(credentialAuthenticator).authenticate("customer", "password");
        verify(tokenGenerator).generate("customer");
    }
}
