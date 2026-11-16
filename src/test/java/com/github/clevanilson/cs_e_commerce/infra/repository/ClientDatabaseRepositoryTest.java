package com.github.clevanilson.cs_e_commerce.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.clevanilson.cs_e_commerce.domain.entity.Client;
import com.github.clevanilson.cs_e_commerce.support.ClientSupport;

@SpringBootTest 
class ClientDatabaseRepositoryTest {
    private final ClientSupport clientSupport = new ClientSupport();
    @Autowired
    private ClientDatabaseRepository sut;

    @BeforeEach 
    void setup() {
        this.clientSupport.setup();
    }
    
    @Test
    void create() {
        var client = clientSupport.create();
        assertEquals(client.getId() == 0, true);
        sut.create(client);
        assertEquals(client.getId() != 0, true);
    }

    @Test 
    void getById() {
        var client = clientSupport.create();
        sut.create(client);
        var savedClient = sut.getById(client.getId());
        assertEquals(client.getId(), savedClient.getId());
        assertEquals(client.getName(), savedClient.getName());
    }

    @Test 
    void getByIdWithNonExistentClient() {
        var savedClient = sut.getById(-1);
        assertEquals(savedClient, null);
    }
}
