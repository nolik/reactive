package com.nolik.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ReactiveTwitterClientConfig {

    @Bean
    WebClient client() {
        return WebClient.create("http://localhost:8081");
    }
}
