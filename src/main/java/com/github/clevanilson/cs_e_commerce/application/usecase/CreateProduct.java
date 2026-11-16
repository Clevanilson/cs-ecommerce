package com.github.clevanilson.cs_e_commerce.application.usecase;

import org.springframework.stereotype.Service;

import com.github.clevanilson.cs_e_commerce.application.repository.ProductRepository;
import com.github.clevanilson.cs_e_commerce.domain.dto.CreateProductInput;
import com.github.clevanilson.cs_e_commerce.domain.dto.ProductOutput;
import com.github.clevanilson.cs_e_commerce.domain.entity.Product;

@Service 
public class CreateProduct {
    private final ProductRepository productRepository;

    public CreateProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductOutput execute(CreateProductInput input) {
        var product = Product.create(
            input.name(),
            input.description(),
            input.price(),
            input.quantity()
        );
        this.productRepository.create(product);
        return new ProductOutput(
            product.getId(),
            product.getName(),
            product.getDescription(), 
            product.getPrice(),
            product.getQuantity()
        );
    }
}
