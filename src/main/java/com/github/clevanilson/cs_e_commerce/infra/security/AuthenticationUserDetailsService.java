package com.github.clevanilson.cs_e_commerce.infra.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.clevanilson.cs_e_commerce.application.repository.UserRepository;

@Service
public class AuthenticationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    
    public AuthenticationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.getByLogin(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new UserDetailsAdapter(user);
    }
}
