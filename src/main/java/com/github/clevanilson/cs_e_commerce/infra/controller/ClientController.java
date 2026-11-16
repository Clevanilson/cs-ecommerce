package com.github.clevanilson.cs_e_commerce.infra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.clevanilson.cs_e_commerce.application.usecase.CreateClient;
import com.github.clevanilson.cs_e_commerce.domain.dto.ClientOutput;
import com.github.clevanilson.cs_e_commerce.domain.dto.CreateClientInput;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("api/clients")
public class ClientController {
    private final CreateClient createClient;

    public ClientController(
        CreateClient createClient
    ) {
        this.createClient = createClient;
    }

    @PostMapping
    public ResponseEntity<ClientOutput> postMethodName(@RequestBody CreateClientInput body) {
        var createdClient = createClient.execute(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }
    
}
