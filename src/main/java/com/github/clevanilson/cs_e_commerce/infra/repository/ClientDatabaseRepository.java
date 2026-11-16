package com.github.clevanilson.cs_e_commerce.infra.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.clevanilson.cs_e_commerce.application.repository.ClientRepository;
import com.github.clevanilson.cs_e_commerce.domain.entity.Client;

@Repository
public class ClientDatabaseRepository implements ClientRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClientDatabaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Client client) {
        var sql = """
            INSERT INTO clients
            (name)
            VALUES
            (?)
            RETURNING id;
        """;
        var id = this.jdbcTemplate.queryForObject(
            sql, 
            Long.class,
            client.getName()
        );
        client.setId(id);
    }

}
