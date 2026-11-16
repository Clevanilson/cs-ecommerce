package com.github.clevanilson.cs_e_commerce.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import com.github.clevanilson.cs_e_commerce.support.ProductSupport;

@SpringBootTest 
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProductDatabaseRepositoryTest {
    private final ProductSupport productSupport = new ProductSupport();
    private final ProductDatabaseRepository sut;

    public ProductDatabaseRepositoryTest(ProductDatabaseRepository sut) {
        this.sut = sut;
    }

    @BeforeEach
    void setup() {
        productSupport.setup();
    }
    
    @Test
    void create() {
        var product = productSupport.create();
        sut.create(product);
        assertEquals(product.getId() != 0, true);
        assertEquals(product.getName(), product.getName());
        assertEquals(product.getDescription(), product.getDescription());
        assertEquals(product.getPrice(), product.getPrice());
        assertEquals(product.getQuantity(), product.getQuantity());
    }

    @Test 
    void getById() {
        var product = productSupport.create();
        sut.create(product);
        var savedProduct = sut.getById(product.getId());
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getDescription(), savedProduct.getDescription());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test 
    void getByIdWithNonExistentProduct() {
        var savedProduct = sut.getById(-1);
        assertEquals(savedProduct, null);
    }
}
