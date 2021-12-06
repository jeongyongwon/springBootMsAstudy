package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configusation 없이는 세팅도 안됨~~
//@Configuration
public class FilterConfig {
    //@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/my-first-name/**")
                        .filters(f -> f.addRequestHeader("first-request","first-request-header")
                                .addResponseHeader("first-response","first-response-header"))
                        .uri("http://localhost:8081/"))
                .route(r -> r.path("/my-second-name/**")
                        .filters(f -> f.addRequestHeader("second-request","second-request-header")
                                .addResponseHeader("second-response","second-response-header"))
                        .uri("http://localhost:8082/"))
                .build();
    }
}
