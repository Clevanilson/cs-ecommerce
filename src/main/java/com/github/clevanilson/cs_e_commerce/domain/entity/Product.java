package com.github.clevanilson.cs_e_commerce.domain.entity;

import com.github.clevanilson.cs_e_commerce.domain.valueobject.Description;
import com.github.clevanilson.cs_e_commerce.domain.valueobject.Name;
import com.github.clevanilson.cs_e_commerce.domain.valueobject.Price;

public class Product {
    private long id;
    private Name name; 
    private Description description;
    private Price price;

    public Product(long id, String name, String description, double price) {
        this.id = id;
        this.name = new Name(name);
        this.description = new Description(description);
        this.price = new Price(price);
    }

    public static Product create(String name, String description, double price) {
        return new Product(0, name, description, price);
    }

    public long getID() {
        return id;
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
}
