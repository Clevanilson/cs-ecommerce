package com.github.clevanilson.cs_e_commerce.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;
import com.github.clevanilson.cs_e_commerce.support.ProductSupport;

@SpringBootTest
class CreateProductTest {
    private final ProductSupport productSupport = new ProductSupport();
    @Autowired 
    private CreateProduct sut;

    @BeforeEach 
    void setup() {
        productSupport.setup();
    }

    @Test 
    void withSuccess() {
        var productDTO = productSupport.createInput();
        var createdProduct = sut.execute(productDTO);
        assertEquals(createdProduct.id() > 0, true);
        assertEquals(createdProduct.name(), productDTO.name());
        assertEquals(createdProduct.description(), productDTO.description());
        assertEquals(createdProduct.price(), productDTO.price());
        assertEquals(createdProduct.quantity(), productDTO.quantity());
    }

    @Test
    void withInvalidData() {
        productSupport.name = "Wrong name ❌";
        var productDTO = productSupport.createInput();
        var exception = assertThrows(
            DomainError.class,
            () -> sut.execute(productDTO)
        );
        assertEquals(exception.getMessage(), "Name must contain only letters, spaces and numbers");
    }
}
