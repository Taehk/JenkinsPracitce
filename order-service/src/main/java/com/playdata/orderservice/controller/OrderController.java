package com.playdata.orderservice.controller;

import com.playdata.orderservice.domain.Order;
import com.playdata.orderservice.dto.RequestCreateOrderDto;
import com.playdata.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {
    private final OrderService orderService;

    @RequestMapping(value = "/health-check", method = RequestMethod.GET)
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Order-Service 등록 완료!");
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<?> registerOrder(@Valid @RequestBody RequestCreateOrderDto orderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.registerOrder(orderDto));
    }

    @RequestMapping(value = "/orders/{userId}/users", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getOrderListByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(orderService.findOrderByUserId(userId)
                    .orElseThrow(
                            () -> new RuntimeException("해당 유저의 주문 내역이 없습니다.")
                    ));
    }

    @RequestMapping(value = "/orders/{productId}/items", method = RequestMethod.GET)
    public List<Order> getOrderListByProductId(@PathVariable String productId){
        return  orderService.findOrderByProductId(productId)
                .orElseThrow(
                        () -> new RuntimeException("해당 상품의 주문 내역이 없습니다.")
                );
    }
//    @RequestMapping(value = "/Orders/all", method = RequestMethod.GET)
//    public ResponseEntity<List<Order>> findAllOrder(){
//        return ResponseEntity.ok(OrderService.findAllOrder());
//    }
//
//    @RequestMapping(value = "/Orders/{uuid}", method = RequestMethod.GET)
//    public ResponseEntity<Order> findOrderByUuid(@PathVariable("uuid") String uuid){
//        return ResponseEntity.ok(OrderService.findOrderByUuid(uuid));
//    }
}
