package com.playdata.userService.feignclient;

import com.playdata.userService.domian.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Order-Service", path= "/order-service")    // order-service로써 유레카에 등록된 서비스와
public interface OrderFeignClient {

    @GetMapping("/orders/{userId}/users") // /orders/{userId}로 호출을 넣으면
    List<Order> getOrderListByUserId(@PathVariable String userId);
                                // 요청 처리
}
