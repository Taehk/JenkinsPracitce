package com.playdata.itmeservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {
    // 멤버 변수로 RabbitTemplate를 생성
    private final RabbitTemplate rabbitTemplate;

    public void sendTestMessage(String message){
        rabbitTemplate.convertAndSend("ITEM_CREATE_QUEUE", message);
    }

    // item 생성
    public void sendCreateItemMessage(String message){
        rabbitTemplate.convertAndSend("ITEM_CREATE_QUEUE", message);
    }
}
