package com.playdata.itmeservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playdata.itmeservice.dto.RequestCreateItemDto;
import com.playdata.itmeservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {
    private final ItemService itemService;
    private final ObjectMapper objectMapper;

    // 큐가 여력이 있을 때를 인지하고 동작하도록 하는 어노테이션
    @RabbitListener(queues = "ITEM_CREATE_QUEUE")
    // Controller에서 HttpServletRequest를 매개변수로 받는 것처럼
    //      우리가 직접 매개 변수를 넣는게 아니라 선언해두는 것
    public void createItem(String message) throws JsonProcessingException {
        // objectMapper.readValue("String형식 Json", 목적객체.class);
        RequestCreateItemDto itemDto =
                objectMapper.readValue(message, RequestCreateItemDto.class);
        // service에서 DTO를 입력받아 DB에 INSERT해주는 로직 호출
        itemService.registItem(itemDto);
    }
}