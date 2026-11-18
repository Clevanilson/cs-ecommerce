package com.github.clevanilson.cs_e_commerce.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.clevanilson.cs_e_commerce.application.repository.ClientRepository;
import com.github.clevanilson.cs_e_commerce.application.repository.ProductRepository;
import com.github.clevanilson.cs_e_commerce.domain.entity.Client;
import com.github.clevanilson.cs_e_commerce.domain.entity.Product;
import com.github.clevanilson.cs_e_commerce.domain.error.ApplicationError;
import com.github.clevanilson.cs_e_commerce.domain.error.NotFoundError;
import com.github.clevanilson.cs_e_commerce.support.ClientSupport;
import com.github.clevanilson.cs_e_commerce.support.OrderSupport;
import com.github.clevanilson.cs_e_commerce.support.ProductSupport;

@SpringBootTest  
public class CreateOrderTest {
    @Autowired   
    private  CreateOrder sut;
    @Autowired 
    private  ClientRepository clientRepository;
    @Autowired  
    private  ProductRepository productRepository;

    private final ClientSupport clientSupport = new ClientSupport();
    private final ProductSupport productSupport = new ProductSupport();
    private final OrderSupport orderSupport = new OrderSupport();
    private Product product;
    private Client client;

    @BeforeEach
    void setup(){
        clientSupport.setup();
        productSupport.setup();
        clientSupport.id = 1;
        productSupport.id = 1;
        client = clientRepository.getById(clientSupport.id);
        product = productRepository.getById(productSupport.id);
        if (client == null) {
            client = clientSupport.create();
            clientRepository.create(client);
        }
        if (product == null) {
            product = productSupport.create();
            productRepository.create(product);
        }
    }

    @Test()
    void execute() {
        orderSupport.clientId = client.getId();
        orderSupport.productId = product.getId();
        var input = orderSupport.createInput();
        var output = sut.execute(input);
        assertEquals(output.items().size(), 1);
        assertEquals(output.id() != 0, true);
        assertEquals(output.items().get(0).id(), input.items().get(0).product_id());
        assertEquals(output.items().get(0).name(), product.getName());
        assertEquals(output.items().get(0).total(), product.getPrice() * input.items().get(0).quantity());
        assertEquals(output.items().get(0).quantity(), input.items().get(0).quantity());
        assertEquals(output.total(), product.getPrice() * input.items().get(0).quantity());
    }

    @Test()
    void executeWithInvalidClient() {
        orderSupport.clientId = 0;
        var input = orderSupport.createInput();
        var exception = assertThrows(NotFoundError.class, () -> sut.execute(input));
        assertEquals(exception.getMessage(), "Client not found");
    }

    @Test()
    void executeWithInvalidProduct() {
        orderSupport.productId = 0;
        var input = orderSupport.createInput();
        var exception = assertThrows(NotFoundError.class, () -> sut.execute(input));
        assertEquals(exception.getMessage(), "Product 0 not found");
    }

    @Test()
    void executeWithInvalidQuantity() {
        orderSupport.quantity = 0;
        var input = orderSupport.createInput();
        var exception = assertThrows(ApplicationError.class, () -> sut.execute(input));
        assertEquals(exception.getMessage(), "Order should contain at least 1 item");
    }
}
