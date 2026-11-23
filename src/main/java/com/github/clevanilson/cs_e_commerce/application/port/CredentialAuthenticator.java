package com.github.clevanilson.cs_e_commerce.application.port;

public interface CredentialAuthenticator {
    String authenticate(String username, String password);
}
