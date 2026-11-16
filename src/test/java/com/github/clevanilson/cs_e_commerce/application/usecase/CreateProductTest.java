package com.github.clevanilson.cs_e_commerce.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.clevanilson.cs_e_commerce.domain.dto.CreateProductInput;
import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

@SpringBootTest
class CreateProductTest {
    @Autowired 
    private CreateProduct sut;

    @Test 
    void withSuccess() {
        var productDTO = new CreateProductInput(
            "Some product",
            "Some description",
            10.50f,
            10
        );
        var createdProduct = sut.execute(productDTO);
        assertEquals(createdProduct.id() > 0, true);
        assertEquals(createdProduct.name(), productDTO.name());
        assertEquals(createdProduct.description(), productDTO.description());
        assertEquals(createdProduct.price(), productDTO.price());
        assertEquals(createdProduct.quantity(), productDTO.quantity());
    }

    @Test
    void withInvalidData() {
        var productDTO = new CreateProductInput(
            "Wrong name ❌",
            "Some description",
            10.50f,
            10
        );
        var exception = assertThrows(
            DomainError.class,
            () -> sut.execute(productDTO)
        );
        assertEquals(exception.getMessage(), "Name must contain only letters, spaces and numbers");
    }
}
