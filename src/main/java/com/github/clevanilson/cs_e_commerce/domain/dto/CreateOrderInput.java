package com.github.clevanilson.cs_e_commerce.domain.dto;

import java.util.List;

public record CreateOrderInput(
    long client_id,
    List<Item> items
) {
    public record Item(
        long product_id,
        int quantity
    ) {}
}
