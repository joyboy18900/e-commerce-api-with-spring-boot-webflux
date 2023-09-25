package com.example.springwebfluxapi.order;

import com.example.springwebfluxapi.product.handle.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/getOrders")
    public ResponseEntity<Flux<OrderResponse>> getOrders() {
        Flux<Order> orders = orderService.findAll();

        Flux<OrderResponse> orderResponses = orders.map(order -> {
            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setOrderId(order.getId());
            orderResponse.setCustomerId(order.getCustomerId());
            orderResponse.setDeliveryAddressId(order.getDeliveryAddressId());
            orderResponse.setOrderDate(order.getOrderDate());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setTotalPrice(order.getTotalPrice());

            return orderResponse;
        });

        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/getOrderById/{orderId}")
    public Mono<ResponseEntity<OrderResponse>> getOrderById(@PathVariable("orderId") Integer orderId) {
        return orderService.findById(orderId)
                .map(order -> {
                    OrderResponse orderResponse = new OrderResponse();
                    orderResponse.setOrderId(order.getId());
                    orderResponse.setCustomerId(order.getCustomerId());
                    orderResponse.setDeliveryAddressId(order.getDeliveryAddressId());
                    orderResponse.setOrderDate(order.getOrderDate());
                    orderResponse.setStatus(order.getStatus());
                    orderResponse.setTotalPrice(order.getTotalPrice());

                    return ResponseEntity.ok(orderResponse);
                })
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with ID " + orderId + " not found")));
    }
}
