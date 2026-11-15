package com.github.clevanilson.cs_e_commerce.application.repository;

import com.github.clevanilson.cs_e_commerce.domain.entity.Product;

public interface ProductRepository {
    public Product create(Product product);
}
