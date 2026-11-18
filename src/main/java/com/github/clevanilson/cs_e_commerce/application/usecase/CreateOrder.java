package com.github.clevanilson.cs_e_commerce.application.usecase;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.github.clevanilson.cs_e_commerce.application.repository.ClientRepository;
import com.github.clevanilson.cs_e_commerce.application.repository.OrderRepository;
import com.github.clevanilson.cs_e_commerce.application.repository.ProductRepository;
import com.github.clevanilson.cs_e_commerce.domain.dto.CreateOrderInput;
import com.github.clevanilson.cs_e_commerce.domain.dto.OrderOutput;
import com.github.clevanilson.cs_e_commerce.domain.entity.Order;
import com.github.clevanilson.cs_e_commerce.domain.entity.Product;
import com.github.clevanilson.cs_e_commerce.domain.error.ApplicationError;
import com.github.clevanilson.cs_e_commerce.domain.error.NotFoundError;

@Service 
public class CreateOrder {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public CreateOrder(
        OrderRepository orderRepository,
        ClientRepository clientRepository,
        ProductRepository productRepository
    ) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public OrderOutput execute(CreateOrderInput input) {
        if (clientRepository.getById(input.client_id()) == null) {
            throw new NotFoundError("Client not found");
        }
        var order = Order.create(input.client_id());
        for (var item : input.items()) {
            var product = productRepository.getById(item.product_id());
            if (product == null) { 
                throw new NotFoundError(
                    String.format("Product %d not found", item.product_id())
                );
            }
            if (item.quantity() > 0) {
                order.addItem(product, item.quantity());
            }
        }
        if (order.getItems().size() == 0) {
            throw new ApplicationError("Order should contain at least 1 item");
        }
        orderRepository.create(order);
        return new OrderOutput(
            order.getId(),
            order.getTotal(),
            order.getItems().stream().map((item) -> new OrderOutput.Item(
                item.getProduct().getId(), 
                item.getProduct().getName(), 
                item.getTotal(), 
                item.getQuantity()
            )).toList()
        );
    }


}
