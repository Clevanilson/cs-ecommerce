package com.github.clevanilson.cs_e_commerce.domain.entity;

import com.github.clevanilson.cs_e_commerce.domain.valueobject.Name;

public class Client {
    private long id;
    private Name name;

    public Client(long id, String name) {
        this.id = id;
        this.name = new Name(name);
    }

    public static Client create(String name) {
        return new Client(0, name);
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }
}
