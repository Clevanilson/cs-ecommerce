package com.github.clevanilson.cs_e_commerce.infra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.clevanilson.cs_e_commerce.application.usecase.CreateOrder;
import com.github.clevanilson.cs_e_commerce.domain.dto.CreateOrderInput;
import com.github.clevanilson.cs_e_commerce.domain.dto.OrderOutput;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("api/orders")
public class OrderController {
    private final CreateOrder createOrder;

    public  OrderController(CreateOrder createOrder) {
        this.createOrder = createOrder;
    }

    @PostMapping
    public ResponseEntity<OrderOutput> createOrder(@RequestBody CreateOrderInput body) {
        var output = this.createOrder.execute(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
    
}
