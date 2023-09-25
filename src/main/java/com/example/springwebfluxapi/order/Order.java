package com.example.springwebfluxapi.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Table("orders")
public class Order implements Serializable {

    @Id
    private Integer id;

    @Column("customer_id")
    private Integer customerId;

    @Column("delivery_address_id")
    private Integer deliveryAddressId;

    @Column("order_date")
    private LocalDate orderDate;

    @Column("status")
    private String status;

    @Column("total_price")
    private Double totalPrice;

    public Order(Integer id, Integer customerId, Integer deliveryAddressId, LocalDate orderDate, String status, Double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.deliveryAddressId = deliveryAddressId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Integer deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
