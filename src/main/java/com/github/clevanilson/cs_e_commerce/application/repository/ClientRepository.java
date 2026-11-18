package com.github.clevanilson.cs_e_commerce.application.repository;

import com.github.clevanilson.cs_e_commerce.domain.entity.Client;

public interface ClientRepository {
    void create(Client Client);
    Client getById(long id);
} 
