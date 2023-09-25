package com.example.springwebfluxapi.order;

import com.example.springwebfluxapi.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Flux<Order> findAll() {

        return orderRepository.findAll();
    }

    public Mono<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }
}
