package com.example.springwebfluxapi.product;

import com.example.springwebfluxapi.product.handle.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProducts")
    public Flux<ResponseEntity<ProductResponse>> getAllProducts() { // @RequestParam(required = false) String productName
        Flux<Product> products = productService.findAll();

        Flux<ResponseEntity<ProductResponse>> productResponses = products.map(product -> {
            ProductResponse productResponse = new ProductResponse();

            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setCategoryId(product.getCategory_id());
            productResponse.setCategoryName(String.valueOf(product.getCategory_id()));
            productResponse.setPrice(product.getPrice());

            return ResponseEntity.ok(productResponse);
        });

        return productResponses;
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
