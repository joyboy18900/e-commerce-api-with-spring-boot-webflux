package com.example.springwebfluxapi.handle;

import reactor.core.publisher.Mono;

public interface ErrorResponseExceptionHandlerResolver {

    Mono<ErrorResponseExceptionHandler> resolve(final Throwable e);
}
