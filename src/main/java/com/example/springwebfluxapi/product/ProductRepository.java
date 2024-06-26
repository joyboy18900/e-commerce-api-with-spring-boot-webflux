package com.example.springwebfluxapi.product;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Integer> {

    Mono<Product> findByName(String name);

    @Query("SELECT p.id, p.name, p.description, p.category_id, pc.name as category_name, p.price " +
            "FROM products p " +
            "LEFT JOIN product_categories pc ON p.category_id = pc.id " +
            "WHERE p.id = :id")
    Mono<Product> findByIdWithCategory(@Param("id") Integer id);

    @Query("SELECT p.id, p.name, p.description, p.category_id, pc.name as category_name, p.price " +
            "FROM products p " +
            "LEFT JOIN product_categories pc ON p.category_id = pc.id")
    Flux<Product> findAllWithProductCategory();
}
