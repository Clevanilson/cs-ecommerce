package com.github.clevanilson.cs_e_commerce.infra.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.clevanilson.cs_e_commerce.application.repository.UserRepository;
import com.github.clevanilson.cs_e_commerce.domain.entity.User;

@Repository 
public class UserDatabaseRepository implements UserRepository {
    private JdbcTemplate jdbcTemplate;

    public UserDatabaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getByLogin(String login) {
        try {
            var sql = """
                SELECT * FROM users
                WHERE login = ?
            """;
            return jdbcTemplate.queryForObject(
                sql, 
                (row, rowNumber) -> new User(row.getString("login"), row.getString("password")),
                login
            );
        }
        catch(Exception exception) {
            if (exception instanceof EmptyResultDataAccessException) {
                return null;
            }
            throw exception;
        }
    }
}
