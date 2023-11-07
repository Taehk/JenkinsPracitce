package com.example.apigatewayserver.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
        // pre filter
            System.out.println("Global Filter default message : " + config.getMessage());
            // pre에서 사용할지 말지 Boolean값으로 판단
            if(config.isPre()){
                System.out.println("Global Pre Filter : " + request.getId());
            }

        // post filter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                // post에서 사용할지 말지 Boolean값으로 판단
                    if(config.isPost()){
                        System.out.println("Global Post Filter : " + response.getStatusCode());
                    }
            }));
        };
    }
    @AllArgsConstructor
    @Getter
    public static class Config{
        private String message;
        private boolean pre;
        private boolean post;
    }
}
