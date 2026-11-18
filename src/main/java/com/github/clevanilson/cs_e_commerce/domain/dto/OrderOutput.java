package com.github.clevanilson.cs_e_commerce.domain.dto;

import java.util.List;

public record OrderOutput(long id, double total, List<OrderOutput.Item> items) {
    public record Item(long id, String name, double total, int quantity) {}
}
