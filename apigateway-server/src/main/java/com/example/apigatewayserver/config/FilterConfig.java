//package com.example.apigatewayserver.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder){
//        return routeLocatorBuilder.routes()
//                // first-service 필터세팅
//                .route( r -> r.path("/first-service/**")
//                        .filters( f -> f.addRequestHeader("f-req", "f-req-v")
//                                .addResponseHeader("f-res", "f-res-v"))
//                        .uri("http://localhost:8001/")
//                )
//                // second-service 필터세팅
//                .route( r -> r.path("/second-service/**")
//                        .filters( f -> f.addRequestHeader("s-req", "s-req-v")
//                                .addResponseHeader("s-res", "s-res-v"))
//                        .uri("http://localhost:8002/"))
//                // 설정 내용
//                .build();   // 빌더패턴 종료
//    }
//}
