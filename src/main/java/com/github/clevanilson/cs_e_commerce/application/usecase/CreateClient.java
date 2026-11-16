package com.github.clevanilson.cs_e_commerce.application.usecase;

import org.springframework.stereotype.Service;

import com.github.clevanilson.cs_e_commerce.application.repository.ClientRepository;
import com.github.clevanilson.cs_e_commerce.domain.dto.ClientOutput;
import com.github.clevanilson.cs_e_commerce.domain.dto.CreateClientInput;
import com.github.clevanilson.cs_e_commerce.domain.entity.Client;

@Service 
public class CreateClient {
    private final ClientRepository clientRepository;

    public CreateClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientOutput execute(CreateClientInput input) {
        var client = Client.create(input.name());
        clientRepository.create(client);
        return new ClientOutput(client.getId(), client.getName());
    }
}
