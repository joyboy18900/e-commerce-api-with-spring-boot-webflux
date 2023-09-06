package com.example.springwebfluxapi.product;

import com.example.springwebfluxapi.product.handle.DuplicateProductNameException;
import com.example.springwebfluxapi.product.handle.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Flux<Product> findAll() {

        return productRepository.findAll();
    }

    public Mono<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public Mono<Product> createProduct(ProductRequest productRequest) {
        return productRepository.findByName(productRequest.getName())
                .flatMap(existingProduct -> {
                    return Mono.<Product>error(new DuplicateProductNameException("Product name already exists."));
                })
                .switchIfEmpty(Mono.defer(() -> {
                    Product product = new Product();
                    product.setName(productRequest.getName());
                    product.setDescription(productRequest.getDescription());
                    product.setCategory_id(productRequest.getCategoryId());
                    product.setPrice(productRequest.getPrice());

                    return productRepository.save(product);
                }));
    }

    public Mono<Product> updateProduct(Integer id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(productRequest.getName());
                    existingProduct.setDescription(productRequest.getDescription());
                    existingProduct.setCategory_id(productRequest.getCategoryId());
                    existingProduct.setPrice(productRequest.getPrice());
                    existingProduct.setUpdate_date(LocalDateTime.now());
                    return productRepository.save(existingProduct);
                })
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with ID " + id + " not found")));
    }

    public Mono<Void> deleteById(Integer id) {
        return productRepository.deleteById(id);
    }
}
