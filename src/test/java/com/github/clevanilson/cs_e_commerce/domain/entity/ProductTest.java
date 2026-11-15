package com.github.clevanilson.cs_e_commerce.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.clevanilson.cs_e_commerce.domain.error.DomainError;

public class ProductTest {
    @Test
    void withValidData() {
        var sut = new Product(1,"Test product", "Some description", 10.50f);
        assertEquals(sut.getID(), 1);
        assertEquals(sut.getName(), "Test product");
        assertEquals(sut.getDescription(), "Some description");
        assertEquals(sut.getPrice(), 10.50f);
    }

    @Test
    void withInvalidName() {
        var exception = assertThrows(
            DomainError.class,
             () -> Product.create("", "Some description", 10.50f)
        );
        assertEquals(exception.getMessage(), "Name must contain at least 3 characters");
    }

    @Test
    void withInvalidDescription() {
        var exception = assertThrows(
            DomainError.class,
             () -> Product.create("Test product", "", 10.50f)
        );
        assertEquals(exception.getMessage(), "Description must contain at least 4 characters");
    }

    @Test
    void withInvalidPrice() {
        var exception = assertThrows(
            DomainError.class,
             () -> Product.create("Test product", "Some description", 0)
        );
        assertEquals(exception.getMessage(), "Price cannot be less than 1");
    }
}
