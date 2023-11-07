package com.playdata.itmeservice.feign;

import com.playdata.itmeservice.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Order-Service", path= "/order-service")
public interface OrderFeignClient {
    @GetMapping("/orders/{productId}/items")
    List<Order> getOrderListByProductId(@PathVariable String productId);
}
