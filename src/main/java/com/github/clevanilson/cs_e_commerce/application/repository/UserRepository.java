package com.github.clevanilson.cs_e_commerce.application.repository;

import com.github.clevanilson.cs_e_commerce.domain.entity.User;

public interface UserRepository {
    User getByLogin(String login);
}
