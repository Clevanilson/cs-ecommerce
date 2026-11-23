package com.github.clevanilson.cs_e_commerce.infra.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.github.clevanilson.cs_e_commerce.application.repository.UserRepository;
import com.github.clevanilson.cs_e_commerce.domain.entity.User;

class AuthenticationUserDetailsServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final AuthenticationUserDetailsService userDetailsService =
        new AuthenticationUserDetailsService(userRepository);

    @Test
    void loadUserByUsernameReturnsInfrastructureUserDetails() {
        when(userRepository.getByLogin("customer")).thenReturn(new User("customer", "encoded-password"));

        var userDetails = userDetailsService.loadUserByUsername("customer");

        assertInstanceOf(UserDetailsAdapter.class, userDetails);
        assertEquals("customer", userDetails.getUsername());
        assertEquals("encoded-password", userDetails.getPassword());
        assertEquals(
            new SimpleGrantedAuthority("USER"),
            userDetails.getAuthorities().iterator().next()
        );
    }

    @Test
    void loadUserByUsernameThrowsWhenUserDoesNotExist() {
        when(userRepository.getByLogin("missing")).thenReturn(null);

        assertThrows(
            UsernameNotFoundException.class,
            () -> userDetailsService.loadUserByUsername("missing")
        );
    }
}
