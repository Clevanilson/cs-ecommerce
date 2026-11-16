package com.github.clevanilson.cs_e_commerce.domain.entity;

import com.github.clevanilson.cs_e_commerce.domain.valueobject.Description;
import com.github.clevanilson.cs_e_commerce.domain.valueobject.Name;
import com.github.clevanilson.cs_e_commerce.domain.valueobject.Price;
import com.github.clevanilson.cs_e_commerce.domain.valueobject.Quantity;

public class Product {
    private long id;
    private Name name; 
    private Description description;
    private Price price;
    private Quantity quantity;

    public Product(long id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = new Name(name);
        this.description = new Description(description);
        this.price = new Price(price);
        this.quantity = new Quantity(quantity);
    }

    public static Product create(String name, String description, double price, int quantity) {
        return new Product(0, name, description, price, quantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public double getPrice() {
        return price.getValue();
    }

    public int getQuantity() {
        return quantity.getValue();
    }
}
