package com.github.clevanilson.cs_e_commerce.infra.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.github.clevanilson.cs_e_commerce.application.repository.ClientRepository;
import com.github.clevanilson.cs_e_commerce.application.repository.ProductRepository;
import com.github.clevanilson.cs_e_commerce.domain.entity.Client;
import com.github.clevanilson.cs_e_commerce.domain.entity.Product;
import com.github.clevanilson.cs_e_commerce.support.ClientSupport;
import com.github.clevanilson.cs_e_commerce.support.OrderSupport;
import com.github.clevanilson.cs_e_commerce.support.ProductSupport;
import com.github.clevanilson.cs_e_commerce.support.TestSupport;

@SpringBootTest 
@AutoConfigureMockMvc  
public class OrderControllerTest {
    @Autowired 
    private MockMvc mockMvc;
    @Autowired 
    private  ClientRepository clientRepository;
    @Autowired  
    private  ProductRepository productRepository;
    private String endpoint = "/api/orders";
    private final ClientSupport clientSupport = new ClientSupport();
    private final ProductSupport productSupport = new ProductSupport();
    private final OrderSupport orderSupport = new OrderSupport();
    private Product product;
    private Client client;

    @BeforeEach 
    void setup(){
        clientSupport.setup();
        productSupport.setup();
        clientSupport.id = 1;
        productSupport.id = 1;
        client = clientRepository.getById(clientSupport.id);
        product = productRepository.getById(productSupport.id);
        if (client == null) {
            client = clientSupport.create();
            clientRepository.create(client);
        }
        if (product == null) {
            product = productSupport.create();
            productRepository.create(product);
        }
    }

    @Test    
    void createOrderSuccess() throws Exception {
        orderSupport.quantity = 1;
        var requestBody = orderSupport.createRequest();
        mockMvc.perform(
            post(endpoint)
            .contentType(TestSupport.CONTENT_TYPE)
            .content(requestBody)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.items").isArray())
        .andExpect(jsonPath("$.items.length()").value(1))
        .andExpect(jsonPath("$.items[0].id").value(product.getId()))
        .andExpect(jsonPath("$.items[0].name").value(product.getName()))
        .andExpect(jsonPath("$.items[0].total").value(product.getPrice()))
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.total").value(product.getPrice()));
    }

    @Test
    void createOrderFail() throws Exception {
        orderSupport.quantity = 0;
        var requestBody = orderSupport.createRequest();
        mockMvc.perform(
            post(endpoint)
            .contentType(TestSupport.CONTENT_TYPE)
            .content(requestBody)
        )
        .andExpect(status().isUnprocessableContent())
        .andExpect(jsonPath("$.message").value("Order should contain at least 1 item"));
    }
}
