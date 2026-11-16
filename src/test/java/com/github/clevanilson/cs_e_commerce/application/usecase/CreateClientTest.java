package com.github.clevanilson.cs_e_commerce.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.clevanilson.cs_e_commerce.domain.dto.CreateClientInput;
import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

@SpringBootTest 
class CreateClientTest {
    @Autowired
    private CreateClient sut;
    
    @Test
    void withSuccess() {
        var input = new CreateClientInput("Client name");
        var output = sut.execute(input);
        assertEquals(input.name(), output.name());
        assertEquals(output.id() > 1, true);
    }

    @Test
    void withInvalidName() {
        var input = new CreateClientInput("");
        var exception = assertThrows(
            DomainError.class,
            () -> sut.execute(input)
        );
        assertEquals(exception.getMessage(), "Name must contain at least 3 characters");
    }
}
