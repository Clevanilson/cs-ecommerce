package com.github.clevanilson.cs_e_commerce.domain.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.clevanilson.cs_e_commerce.domain.valueobject.Quantity;

public class Order {
    private long id;
    private long clientId;
    private Order.Status status;
    private List<Order.Item> items;
    private double total;

    public Order(long id, long clientId) {
        this.id = id;
        this.clientId = clientId;
        this.status = Order.Status.PENDING;
        this.items = new ArrayList<Order.Item>();
        this.total = 0f;
    }

    public static Order create(Long clientId) {
        return new Order(0, clientId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public Order.Status getStatus() {
        return status;
    }

    public double getTotal() {
        return total;
    }

    public List<Order.Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(Product product, int quantity) {
        var item = new Order.Item(product, quantity);
        total += item.getTotal();
        items.add(item);
    }

    public enum Status {
        PENDING("pending");

        private String description;

        Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public class Item {
        private Product product;
        private Quantity quantity;

        public Item(Product product, int quantity) {
            this.product = product;
            this.quantity = new Quantity(quantity);
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity.getValue();
        }

        public double getTotal() {
            return this.product.getPrice() * this.quantity.getValue();
        }
    }
}
