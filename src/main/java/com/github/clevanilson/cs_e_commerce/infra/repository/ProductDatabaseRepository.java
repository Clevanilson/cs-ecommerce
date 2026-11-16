package com.github.clevanilson.cs_e_commerce.infra.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.clevanilson.cs_e_commerce.application.repository.ProductRepository;
import com.github.clevanilson.cs_e_commerce.domain.entity.Product;

@Repository 
public class ProductDatabaseRepository implements ProductRepository {
    private JdbcTemplate jdbcTemplate;

    public ProductDatabaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override 
    public Product create(Product product) {
        var sql = """
        INSERT INTO products
            (name, description, price, quantity)
        VALUES
            (?, ?, ?, ?)
        RETURNING id;
        """;
        var id = this.jdbcTemplate.queryForObject(
            sql,
            Long.class,
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity()
        );
        return new Product(
            id, 
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity()
        );
    }
}
