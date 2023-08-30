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
            productResponse.setCategoryId(product.getCategory_id());
            productResponse.setCategoryName(String.valueOf(product.getCategory_id()));
            productResponse.setPrice(product.getPrice());

            return productResponse;
        });

        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductResponse>> getProductById(@PathVariable("id") Integer id) {
        return productService.findById(id)
            .map(prod -> {
                ProductResponse response = new ProductResponse();

                response.setName(prod.getName());
                response.setDescription(prod.getDescription());
                response.setCategoryId(prod.getCategory_id());
                response.setCategoryName(String.valueOf(prod.getCategory_id()));
                response.setPrice(prod.getPrice());

                return ResponseEntity.ok(response);
            })
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product with ID " + id + " not found")));
    }

    @PostMapping("/createProduct")
    public Mono<Product> createProduct(@RequestBody @Validated ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @PutMapping("/updateProduct/{id}")
    public Mono<ResponseEntity<ProductResponse>> updateProduct(@PathVariable("id") Integer id, @RequestBody @Validated ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest)
                .map(product -> {
                    ProductResponse response = new ProductResponse();
                    response.setName(product.getName());
                    response.setDescription(product.getDescription());
                    response.setCategoryId(product.getCategory_id());
                    response.setCategoryName(String.valueOf(product.getCategory_id()));
                    response.setPrice(product.getPrice());
                    return ResponseEntity.ok(response);
                });
    }

    @DeleteMapping("/deleteProductById/{id}")
    public Mono<ResponseEntity<Void>> deleteProductById(@PathVariable("id") Integer id) {
        return productService.deleteById(id)
                .thenReturn(ResponseEntity.noContent().<Void>build());
    }

    @DeleteMapping("/deleteAll")
    public Mono<ResponseEntity<Void>> deleteAllProducts() {
        return productService.deleteAll()
                .thenReturn(ResponseEntity.noContent().<Void>build());
    }
}
