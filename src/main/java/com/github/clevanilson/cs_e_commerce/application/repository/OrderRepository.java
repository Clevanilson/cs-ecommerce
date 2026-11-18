package com.github.clevanilson.cs_e_commerce.application.repository;

import com.github.clevanilson.cs_e_commerce.domain.entity.Order;

public interface OrderRepository {
    void create(Order order);
}
