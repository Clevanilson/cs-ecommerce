package com.github.clevanilson.cs_e_commerce.infra.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.github.clevanilson.cs_e_commerce.support.TestSupport;

@SpringBootTest 
@AutoConfigureMockMvc 
class ClientControllerTest {
    @Autowired 
    private MockMvc mockMvc;
    private String endpont = "/api/clients";
    
    @Test 
    void postCreated() throws Exception{
        var requestBody = """
            {
                "name": "Test client"
            }
        """;
        var responseBody = """
            {
                "name": "Test client"
            }
        """;
        mockMvc.perform(
            post(this.endpont)
                .contentType(TestSupport.CONTENT_TYPE)
                .content(requestBody)
        )
        .andExpect(status().isCreated())
        .andExpect(content().json(responseBody))
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(content().json(responseBody));
    }

    @Test 
    void postBadRequest() throws Exception{
        var requestBody = """
            {
                "name": "Te"
            }
        """;
        var responseBody = """
            {
                "message": "Name must contain at least 3 characters"
            }
        """;
        mockMvc.perform(
            post(this.endpont)
                .contentType(TestSupport.CONTENT_TYPE)
                .content(requestBody)
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().json(responseBody));
    }
}
