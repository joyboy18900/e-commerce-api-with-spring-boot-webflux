package com.example.springwebfluxapi.orderDetail;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface OrderDetailRepository extends R2dbcRepository<OrderDetail, Integer> {

}