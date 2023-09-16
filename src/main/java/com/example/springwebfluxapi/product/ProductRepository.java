package com.example.springwebfluxapi.product;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Integer> {

    Mono<Product> findByName(String name);

    @Query("SELECT p.*, pc.name as \"categoryName\" FROM products p " +
            "JOIN product_categories pc ON p.category_id = pc.id")
    Flux<ProductResponse> findAllWithProductCategory();
}
