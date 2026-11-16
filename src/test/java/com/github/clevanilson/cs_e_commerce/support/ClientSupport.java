package com.github.clevanilson.cs_e_commerce.support;

import com.github.clevanilson.cs_e_commerce.domain.dto.CreateClientInput;
import com.github.clevanilson.cs_e_commerce.domain.entity.Client;

public class ClientSupport {
    public long id;
    public String name;

    public void setup() {
        this.id = 0;
        this.name = "Test client name";
    }

    public Client create() {
        return new Client(id, name);
    }

    public CreateClientInput createInput() {
        return new CreateClientInput(name);
    }
}
