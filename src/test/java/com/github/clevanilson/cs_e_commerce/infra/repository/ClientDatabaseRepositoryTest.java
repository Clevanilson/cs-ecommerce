package com.github.clevanilson.cs_e_commerce.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.clevanilson.cs_e_commerce.domain.entity.Client;

@SpringBootTest 
class ClientDatabaseRepositoryTest {
    @Autowired
    private ClientDatabaseRepository sut;
    
    @Test
    void create() {
        var client = Client.create(
            String.format("Client %d", (int) Math.random() * 1000)
        );
        var createdClient = sut.create(client);
        assertEquals(createdClient.getId() > 0, true);
        assertEquals(createdClient.getName(), client.getName());
    }
}
