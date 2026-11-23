package com.github.clevanilson.cs_e_commerce.infra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.clevanilson.cs_e_commerce.domain.dto.LoginInput;
import com.github.clevanilson.cs_e_commerce.application.usecase.Login;
import com.github.clevanilson.cs_e_commerce.domain.dto.LoginInput;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("api/auth")
public class UserController {
    private final Login login;

    public UserController(Login login) {
        this.login = login;
    }


    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginInput body) {
        return ResponseEntity.ok().body(login.execute(body));
    }
    
    
}
