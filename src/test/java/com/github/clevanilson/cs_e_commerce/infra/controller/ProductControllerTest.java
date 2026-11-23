package com.github.clevanilson.cs_e_commerce.infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc 
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void postProductsWithoutAuthenticationForbidden() throws Exception {
        mockMvc.perform(post("/api/products"))
                .andExpect(status().isForbidden());
    }

    @Test 
    @WithMockUser
    void postProductsSuccess() throws Exception {
        var requestBody = """
            {
                "name": "Test product",
                "description": "Some description",
                "price": 10,
                "quantity": 10
            }
        """;
        var responseBody = """
            {
                "name": "Test product",
                "description": "Some description",
                "price": 10,
                "quantity": 10
            }
        """;
        mockMvc.perform(
            post("/api/products")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(content().json(responseBody));
    }

    @Test 
    @WithMockUser
    void postProductsBadrequest() throws Exception {
        var requestBody = """
            {
                "name": "Test product",
                "description": "Some description",
                "price": -10,
                "quantity": 1
            }
        """;
        var responseBody = """
            {
                "message": "Price cannot be less than 1"
            }
        """;
        mockMvc.perform(
            post("/api/products")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(responseBody));
    }
}
