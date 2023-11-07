package com.playdata.orderservice.service;

import com.playdata.orderservice.domain.Order;
import com.playdata.orderservice.dto.RequestCreateOrderDto;
import com.playdata.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order registerOrder(RequestCreateOrderDto orderDto){
        Order order = orderRepository.save(orderDto.toEntity());
        return orderRepository.findOrderByOrderId(order.getOrderId());
    }

    public Optional<List<Order>> findOrderByUserId(String userId){
        return orderRepository.findOrderByUserId(userId);
    }
    public Optional<List<Order>> findOrderByProductId(String productId){
        return orderRepository.findOrderByProductId(productId);
    }
//    public List<Order> findAllItem(){
//        return orderRepository.findAll();
//    }
//
}
