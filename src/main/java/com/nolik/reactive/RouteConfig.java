package com.nolik.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


@Configuration
@RequiredArgsConstructor
public class RouteConfig {
    private final UserHandler userHandler;

    @Bean
    RouterFunction<?> routes(){
        return RouterFunctions. route()
                .GET("/users", accept(APPLICATION_JSON), userHandler::listOfUsers)
                .build();
    }
}
