package com.github.clevanilson.cs_e_commerce.infra.repository;

import org.springframework.dao.EmptyResultDataAccessException;
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
    public void create(Product product) {
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
        product.setId(id);
    }

    @Override
    public Product getById(long id) {
        try {
            var sql = """
            SELECT name, description, price, quantity
            FROM products
            WHERE id = ?
            """;
            return jdbcTemplate.queryForObject(
                sql,
                (row, rowNumber) -> new Product(
                    id, 
                    row.getString("name"),
                    row.getString("description"),
                    row.getDouble("price"),
                    row.getInt("quantity")
                ),
                id
            );
        }
        catch(Exception exception) {
            if (exception instanceof EmptyResultDataAccessException) return null;
            throw exception;
        }
    }
}
