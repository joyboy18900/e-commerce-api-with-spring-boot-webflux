package com.example.springwebfluxapi.handle.impl;

import com.example.springwebfluxapi.model.ErrorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ErrorResponseRootExceptionHandler extends ErrorResponseExceptionHandlerAdapter<Exception> {

    @Override
    public Class<Exception> getTypeClass() {
        return Exception.class;
    }

    @Override
    protected Mono<ErrorResponse> buildError(final ServerWebExchange exchange, final Exception e) {
        return Mono.fromCallable(() -> {
            return ErrorResponse.serverError();
        });
    }
}
