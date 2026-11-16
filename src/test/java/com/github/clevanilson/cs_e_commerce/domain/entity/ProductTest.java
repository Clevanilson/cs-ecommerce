package com.github.clevanilson.cs_e_commerce.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;
import com.github.clevanilson.cs_e_commerce.support.ProductSupport;

public class ProductTest {
    private ProductSupport productSupport = new ProductSupport();

    @BeforeEach 
    void setup() {
        productSupport.setup();
    }
    
    @Test
    void withValidData() {
        var sut = productSupport.create();
        assertEquals(sut.getId(), productSupport.id);
        assertEquals(sut.getName(), productSupport.name);
        assertEquals(sut.getDescription(), productSupport.description);
        assertEquals(sut.getPrice(), productSupport.price);
        assertEquals(sut.getQuantity(), productSupport.quantity);
    }

    @Test
    void withInvalidName() {
        productSupport.name = "";
        var exception = assertThrows(
            DomainError.class,
             () -> productSupport.create()
        );
        assertEquals(exception.getMessage(), "Name must contain at least 3 characters");
    }

    @Test
    void withInvalidDescription() {
        productSupport.description = "";
        var exception = assertThrows(
            DomainError.class,
             () -> productSupport.create()
        );
        assertEquals(exception.getMessage(), "Description must contain at least 4 characters");
    }

    @Test
    void withInvalidPrice() {
        productSupport.price = 0;
        var exception = assertThrows(
            DomainError.class,
             () -> productSupport.create()
        );
        assertEquals(exception.getMessage(), "Price cannot be less than 1");
    }

    @Test
    void withInvalidQuantity() {
        productSupport.quantity = -1;
        var exception = assertThrows(
            DomainError.class,
             () -> productSupport.create()
        );
        assertEquals(exception.getMessage(), "Quantity cannot be negative");
    }

    @Test
    void setId() {
        var sut = productSupport.create();
        sut.setId(2);
        assertEquals(sut.getId(), 2);
    }
}
