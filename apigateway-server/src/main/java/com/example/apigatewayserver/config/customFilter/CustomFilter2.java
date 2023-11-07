package com.example.apigatewayserver.config.customFilter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

//@Component
//public class CustomFilter2 extends AbstractGatewayFilterFactory<CustomFilter2.Config> {
//    public CustomFilter2() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        GatewayFilter gatewayFilter = new OrderedGatewayFilter((exchange, chain) -> {
//            // 필터 내용
//        }, Ordered.HIGHEST_PRECEDENCE);
//        // 구성 순위 조정이 가능하다.
//    }
//    public static class Config{
//
//    }
//}
