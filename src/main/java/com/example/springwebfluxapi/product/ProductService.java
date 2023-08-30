package com.example.springwebfluxapi.product;

import com.example.springwebfluxapi.product.handle.DuplicateProductNameException;
import com.example.springwebfluxapi.product.handle.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

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
                    Product product = new Product(null, productRequest.getName(), productRequest.getDescription(), productRequest.getCategory_id(), productRequest.getPrice(), LocalDate.now(), LocalDate.now());
                    return productRepository.save(product);
                }));
    }

    public Mono<Product> updateProduct(Integer id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(productRequest.getName());
                    existingProduct.setDescription(productRequest.getDescription());
                    existingProduct.setCategory_id(productRequest.getCategory_id());
                    existingProduct.setPrice(productRequest.getPrice());
                    existingProduct.setUpdate_date(LocalDate.now());
                    return productRepository.save(existingProduct);
                })
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with ID " + id + " not found")));
    }

    public Mono<Void> deleteById(Integer id) {
        return productRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return productRepository.deleteAll();
    }
}
