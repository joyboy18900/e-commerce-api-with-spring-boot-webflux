package com.example.springwebfluxapi.order;

import java.time.LocalDate;

public class OrderResponse {

    private Integer orderId;
    private Integer customerId;
    private Integer deliveryAddressId;
    private LocalDate orderDate;
    private String status;
    private Double totalPrice;

    public OrderResponse(Integer orderId, Integer customerId, Integer deliveryAddressId, LocalDate orderDate, String status, Double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryAddressId = deliveryAddressId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public OrderResponse() {

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
