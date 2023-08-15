package com.example.springwebfluxapi.handle.impl;

import com.example.springwebfluxapi.exception.InvalidUsernamePasswordException;
import com.example.springwebfluxapi.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ErrorResponseInvalidUsernamePasswordExceptionHandler extends ErrorResponseExceptionHandlerAdapter<InvalidUsernamePasswordException> {

    @Override
    public Class<InvalidUsernamePasswordException> getTypeClass() {
        return InvalidUsernamePasswordException.class;
    }

    @Override
    protected Mono<ErrorResponse> buildError(final ServerWebExchange exchange, final InvalidUsernamePasswordException e) {
        return Mono.fromCallable(() -> {
            return ErrorResponse.builder()
                    .error("invalid_username_password")
                    .errorDescription("invalid username or password")
                    .errorStatus(HttpStatus.BAD_REQUEST.value())
                    .build();
        });
    }
}