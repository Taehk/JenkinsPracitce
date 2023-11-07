package com.playdata.itmeservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playdata.itmeservice.domain.Item;
import com.playdata.itmeservice.dto.RequestCreateItemDto;
import com.playdata.itmeservice.dto.ResponseBuyItemDto;
import com.playdata.itmeservice.feign.OrderFeignClient;
import com.playdata.itmeservice.repository.ItemRepository;
import com.playdata.itmeservice.util.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    // 직렬화, 역직렬화 담당 라이브러리
    private final ObjectMapper objectMapper;
    private final ItemRepository itemRepository;
    private final OrderFeignClient orderFeignClient;
    private final Producer producer;

    public void registItem(RequestCreateItemDto itemDto){
        itemRepository.save(itemDto.toEntity());
    }

    public List<Item> findAllItem(){
        return itemRepository.findAll();
    }

    public Item findItemByUuid(String uuid){
        return itemRepository.findItemByProductName(uuid).orElseThrow(
                () -> new RuntimeException("존재하지 않는 Item 입니다.")
        );
    }

    public ResponseBuyItemDto findItemInfoByProductId(String productId){
        // 해당 아이템 조회
        ResponseBuyItemDto itemDto = new ResponseBuyItemDto(
                itemRepository.findItemByProductName(productId)
                        .orElseThrow( ()
                                -> new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 상품입니다.")
                        )
        );
        itemDto.setOrderList(
                orderFeignClient.getOrderListByProductId(productId)
                );
        return itemDto;
    }

    public void publishTestMessage(String message){
        producer.sendTestMessage(message);
    }

    public void publishCreateItemMessage(RequestCreateItemDto requestCreateItemDto)
            // objectMapper 직렬화 시에 JsonProcessingException에 대한 예외처리 필요
            throws JsonProcessingException {
        // DTO를 json(String)형태로 직렬화
        String message = objectMapper.writeValueAsString(requestCreateItemDto);
        producer.sendTestMessage(message);
    }
}
