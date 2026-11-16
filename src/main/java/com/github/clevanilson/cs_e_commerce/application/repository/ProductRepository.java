package com.github.clevanilson.cs_e_commerce.application.repository;

import com.github.clevanilson.cs_e_commerce.domain.entity.Product;

public interface ProductRepository {
    public void create(Product product);
    public Product getById(long id);
}
