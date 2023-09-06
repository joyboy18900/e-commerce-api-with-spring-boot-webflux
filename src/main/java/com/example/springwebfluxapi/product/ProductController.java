package com.example.springwebfluxapi.product;

import com.example.springwebfluxapi.product.handle.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Flux<ProductResponse>> getAllProducts() { // @RequestParam(required = false) String productName
        Flux<Product> products = productService.findAll();

        Flux<ProductResponse> productResponses = products.map(product -> {
            ProductResponse productResponse = new ProductResponse();

            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setCategoryId(product.getCategoryId());
            productResponse.setCategoryName(String.valueOf(product.getCategoryId()));
            productResponse.setPrice(product.getPrice());

            return productResponse;
        });

        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/getProductById/{productId}")
    public Mono<ResponseEntity<ProductResponse>> getProductById(@PathVariable("productId") Integer productId) {
        return productService.findById(productId)
            .map(prod -> {
                ProductResponse response = new ProductResponse();

                response.setName(prod.getName());
                response.setDescription(prod.getDescription());
                response.setCategoryId(prod.getCategoryId());
                response.setCategoryName(String.valueOf(prod.getCategoryId()));
                response.setPrice(prod.getPrice());

                return ResponseEntity.ok(response);
            })
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with ID " + productId + " not found")));
    }

    @PostMapping("/createProduct")
    public Mono<Product> createProduct(@RequestBody @Validated ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @PutMapping("/updateProduct/{productId}")
    public Mono<ResponseEntity<ProductResponse>> updateProduct(@PathVariable("productId") Integer productId, @RequestBody @Validated ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest)
                .map(product -> {
                    ProductResponse response = new ProductResponse();
                    response.setName(product.getName());
                    response.setDescription(product.getDescription());
                    response.setCategoryId(product.getCategoryId());
                    response.setCategoryName(String.valueOf(product.getCategoryId()));
                    response.setPrice(product.getPrice());
                    return ResponseEntity.ok(response);
                });
    }

    @DeleteMapping("/deleteProductById/{productId}")
    public Mono<ResponseEntity<Void>> deleteProductById(@PathVariable("productId") Integer productId) {
        return productService.deleteById(productId)
                .thenReturn(ResponseEntity.noContent().<Void>build());
    }
}
