package com.example.springwebfluxapi.product;


import com.example.springwebfluxapi.model.ErrorResponse;
import com.example.springwebfluxapi.product.handle.DuplicateProductNameException;
import com.example.springwebfluxapi.product.handle.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleProductNotFound(ProductNotFoundException ex, ServerWebExchange exchange) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("24000");
        errorResponse.setDescription(ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse));
    }

    @ExceptionHandler(DuplicateProductNameException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleProductDuplicateName(DuplicateProductNameException ex, ServerWebExchange exchange) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("25000");
        errorResponse.setDescription(ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse));
    }
}
