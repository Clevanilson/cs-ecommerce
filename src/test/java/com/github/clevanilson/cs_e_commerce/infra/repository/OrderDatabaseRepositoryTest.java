package com.github.clevanilson.cs_e_commerce.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.clevanilson.cs_e_commerce.domain.entity.Order;
import com.github.clevanilson.cs_e_commerce.domain.entity.Product;

@SpringBootTest 
class OrderDatabaseRepositoryTest {
    @Autowired 
    private OrderDatabaseRepository sut;

    @Test
    void create() {
        var order = new Order(1, 1);
        var product = new Product(1, "abcd", "abcd", 10, 1);
        order.addItem(product, 3);
        sut.create(order);
        assertEquals(order.getId() != 0, true);
    }
}
