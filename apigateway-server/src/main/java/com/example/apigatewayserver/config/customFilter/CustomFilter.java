package com.example.apigatewayserver.config.customFilter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    // Generic 내부에 설정에 필요한 자료형을 넣어줘야하는데
    // CustomFilter의 설정 클래스는 CustomFilter 내부의 Config로 사용할 것
        // Config를 이너클래스로 생성한 이유? -> 응집도를 높여주기 위해
    public CustomFilter(){
        super(Config.class);
    }
    public static class Config{

    }
    @Override
    public GatewayFilter apply(Config config) {
        // import org.springframework.http.server.reactive.ServerHttpRequest;
        // import org.springframework.http.server.reactive.ServerHttpResponse;
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // pre-filter는 그냥 작성하면 작동
            System.out.println("Custom pre filter : " + request.getId());

            // post-filter는 return 구문 속에 코드를 작성해서 만듦
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    // Mono -> 비동기방식 서버에서 단일값 전달할 때 사용하는 양식
                System.out.println("Custom post filter : " + response.getStatusCode());
            }));
        };
    }
}
