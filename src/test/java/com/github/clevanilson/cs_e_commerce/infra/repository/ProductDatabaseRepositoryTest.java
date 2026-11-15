package com.github.clevanilson.cs_e_commerce.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import com.github.clevanilson.cs_e_commerce.domain.entity.Product;

@SpringBootTest 
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProductDatabaseRepositoryTest {
    private final ProductDatabaseRepository sut;

    public ProductDatabaseRepositoryTest(ProductDatabaseRepository sut) {
        this.sut = sut;
    }
    
    @Test
    void create() {
        var product = Product.create(
            String.format("Product %d", (int) Math.random() * 1000),
            "Some description",
            10.50f
        );
        var createdProduct = sut.create(product);
        assertEquals(createdProduct.getID() > 0, true);
        assertEquals(createdProduct.getName(), product.getName());
        assertEquals(createdProduct.getDescription(), product.getDescription());
        assertEquals(createdProduct.getPrice(), product.getPrice());
    }
}
