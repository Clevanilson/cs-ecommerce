package com.github.clevanilson.cs_e_commerce.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.clevanilson.cs_e_commerce.application.usecase.CreateProduct;
import com.github.clevanilson.cs_e_commerce.domain.dto.CreateProductInput;
import com.github.clevanilson.cs_e_commerce.domain.dto.ProductOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/products")
public class ProductController {
    private final CreateProduct createProduct;

    public ProductController(
        CreateProduct createProduct
    ) {
        this.createProduct = createProduct;
    }

    @PostMapping
    public ResponseEntity<ProductOutput> createProduct(@RequestBody CreateProductInput body) {
        var response = this.createProduct.execute(body);
        return ResponseEntity.status(201).body(response);
    }
}
