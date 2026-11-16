package com.github.clevanilson.cs_e_commerce.support;

import com.github.clevanilson.cs_e_commerce.domain.entity.Product;

public class ProductSupport {
    public long id;
    public String name;
    public String description;
    public double price;
    public int quantity;

    public ProductSupport() {
        setup();
    }

    public void setup() {
        this.id = 0;
        this.name = "Test product name";
        this.description = "Test description";
        this.price = 10.50f;
        this.quantity = 10;
    }

    public Product create() {
        return new Product(id, name, description, price, quantity);
    }
}
