package com.github.clevanilson.cs_e_commerce.infra.repository;

import org.springframework.dao.EmptyResultDataAccessException;
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
        var id = jdbcTemplate.queryForObject(
            sql, 
            Long.class,
            client.getName()
        );
        client.setId(id);
    }

    @Override
    public Client getById(long id) {
        try {
            var sql = """
            SELECT name
            FROM clients
            WHERE id = ?;
            """;
            return jdbcTemplate.queryForObject(
                sql,
                (row, rowNumber) -> new Client(
                    id, 
                    row.getString("name")
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
