package com.github.clevanilson.cs_e_commerce.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OrderTest {
    @Test    
    void withValidData() {
        var sut = new Order(1, 2);
        assertEquals(sut.getId(), 1);
        assertEquals(sut.getClientId(), 2);
        assertEquals(sut.getStatus(), Order.Status.PENDING);
        assertEquals(sut.getTotal(), 0);
    }

    @Test
    void addingAnItems() {
        var product1 = createProduct(10);
        var product2 = createProduct(15);
        var sut = createOrder();
        sut.addItem(product1, 1);
        sut.addItem(product2, 2);
        assertEquals(sut.getTotal(), 40);
        assertEquals(sut.getItems().get(0).getProduct(), product1);
        assertEquals(sut.getItems().get(0).getQuantity(), 1);
        assertEquals(sut.getItems().get(0).getTotal(), 10);
        assertEquals(sut.getItems().get(1).getProduct(), product2);
        assertEquals(sut.getItems().get(1).getQuantity(), 2);
        assertEquals(sut.getItems().get(1).getTotal(), 30);
        assertEquals(sut.getItems().size(), 2);
    }

    private Order createOrder() {
        return new Order(1, 1);
    }

    private Product createProduct(double price) {
        var productId = (int) Math.floor(Math.random() * 1000);
        return new Product(productId, String.format("Product %d", productId), "Test", price, 10);
    }
}

