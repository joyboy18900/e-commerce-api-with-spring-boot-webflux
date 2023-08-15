package com.example.springwebfluxapi.handle.impl;

import com.example.springwebfluxapi.handle.ErrorResponseExceptionHandlerResolver;
import com.example.springwebfluxapi.handle.ErrorResponseProducer;
import com.example.springwebfluxapi.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Order(-2)
@RequiredArgsConstructor
public class ServerWebExceptionHandler implements WebExceptionHandler {

    private final ErrorResponseProducer producer;

    private final ErrorResponseExceptionHandlerResolver resolver;

    @Override
    public Mono<Void> handle(final ServerWebExchange exchange, final Throwable e) {
        log.warn("error => ", e);
        return resolver.resolve(e)
                .flatMap(handler -> (Mono<ErrorResponse>)handler.handle(exchange, e))
                .flatMap(err -> producer.produce(err, exchange));
    }
}