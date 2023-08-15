package com.example.springwebfluxapi.controller;

import com.example.springwebfluxapi.entity.Product;
import com.example.springwebfluxapi.model.ProductRequest;
import com.example.springwebfluxapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProducts")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> getAllProducts(@RequestParam(required = false) String title) {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Product> getProductById(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@RequestBody @Validated ProductRequest productRequest) {
        return productService.save(productRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable("id") Integer id) {
        return productService.deleteById(id);
    }
}
