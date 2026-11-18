package com.github.clevanilson.cs_e_commerce.support;

import java.util.ArrayList;
import java.util.List;

import com.github.clevanilson.cs_e_commerce.domain.dto.CreateOrderInput;

public class OrderSupport {
    public long clientId;
    public long productId;
    public int quantity;

    public OrderSupport() {
        setup();
    }

    public void setup() {
        clientId = 1;
        productId = 1;
        quantity = 10;
    }

    public CreateOrderInput createInput() {
        return new CreateOrderInput(
            clientId, 
            new ArrayList<>(List.of(new CreateOrderInput.Item(productId, quantity)))
        );
    }

    public String createRequest() {
        return String.format("""
            {
                "client_id": %d,
                "items": [
                    { "product_id": %d, "quantity": %d }
                ]
            }
            """, 
            clientId,
            productId,
            quantity
        );
    }
}
