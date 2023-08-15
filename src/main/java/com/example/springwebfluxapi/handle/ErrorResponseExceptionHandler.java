package com.example.springwebfluxapi.handle;

import com.example.springwebfluxapi.model.ErrorResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface ErrorResponseExceptionHandler<E extends Throwable> {

    Class<E> getTypeClass();

    Mono<ErrorResponse> handle(final ServerWebExchange exchange, final E e);
}
