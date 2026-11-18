package com.github.clevanilson.cs_e_commerce.infra.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.clevanilson.cs_e_commerce.application.repository.OrderRepository;
import com.github.clevanilson.cs_e_commerce.domain.entity.Order;

@Repository 
public class OrderDatabaseRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderDatabaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public void create(Order order) {
        var orderSQL = """
            INSERT INTO orders
            (client_id, total, status)
            VALUES
            (?, ?, ?)
            RETURNING id;
        """;
        var id = jdbcTemplate.queryForObject(
            orderSQL,
            Long.class,
            order.getClientId(),
            order.getTotal(),
            order.getStatus().getDescription()
        );
        order.setId(id);
        var orderItemsSQL = """
            INSERT INTO order_items
            (order_id, product_id, quantity, price)
            VALUES
            (?, ?, ?, ?);
        """;
        var items = order.getItems();
        jdbcTemplate.batchUpdate(
            orderItemsSQL,
            items,
            items.size(),
            (preparedStatement, item) -> {
                preparedStatement.setLong(1, order.getId());
                preparedStatement.setLong(2, item.getProduct().getId());
                preparedStatement.setLong(3, item.getQuantity());
                preparedStatement.setDouble(4, item.getTotal());
            }
        );
    }
}
