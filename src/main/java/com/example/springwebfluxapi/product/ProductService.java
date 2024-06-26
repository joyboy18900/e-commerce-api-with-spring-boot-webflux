package com.example.springwebfluxapi.product;

import com.example.springwebfluxapi.product.handle.DuplicateProductNameException;
import com.example.springwebfluxapi.product.handle.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Flux<ProductResponse> getAllProductsWithProductType() {
        return productRepository.findAllWithProductCategory()
                .map(this::mapProductToProductResponse);
    }

    public Mono<ProductResponse> findById(Integer productId) {
        return productRepository.findByIdWithCategory(productId)
                .map(this::mapProductToProductResponse)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with ID " + productId + " not found")));
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
                    product.setCategoryId(productRequest.getCategoryId());
                    product.setPrice(productRequest.getPrice());

                    return productRepository.save(product);
                }));
    }

    public Mono<Product> updateProduct(Integer id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(productRequest.getName());
                    existingProduct.setDescription(productRequest.getDescription());
                    existingProduct.setCategoryId(productRequest.getCategoryId());
                    existingProduct.setPrice(productRequest.getPrice());
                    existingProduct.setUpdateDate(LocalDateTime.now());
                    return productRepository.save(existingProduct);
                })
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with ID " + id + " not found")));
    }

    public Mono<Void> deleteById(Integer id) {
        return productRepository.deleteById(id);
    }

    private ProductResponse mapProductToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategoryId(product.getCategoryId());
        productResponse.setCategoryName(product.getCategoryName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }
}
