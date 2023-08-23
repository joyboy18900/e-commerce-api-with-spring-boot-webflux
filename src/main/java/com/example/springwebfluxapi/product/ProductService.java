package com.example.springwebfluxapi.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

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

    public Mono<Product> save(ProductRequest productRequest) {

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory_id(productRequest.getCategory_id());
        product.setPrice(productRequest.getPrice());

        return productRepository.save(product);
    }

    public Mono<Product> update(Integer id, Product product) {
        return productRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        product.setId(id);
                        return productRepository.save(product);
                    }
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(Integer id) {
        return productRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return productRepository.deleteAll();
    }
}
