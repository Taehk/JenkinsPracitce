package com.playdata.itmeservice.dto;


import com.playdata.itmeservice.domain.Item;
import com.playdata.itmeservice.domain.Order;
import lombok.*;

import java.util.List;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder @ToString
public class ResponseBuyItemDto {
    private String productId;
    private String productName;
    private Long stock;
    private Long pricePerItem;
    private String userId;
    private String orderId;

    private List<Order> orderList = List.of();


    public ResponseBuyItemDto(Item item) {
        productId = item.getProductId();
        productName = item.getProductName();
        stock = item.getStock();
        pricePerItem = item.getPricePerItem();
    }
}
