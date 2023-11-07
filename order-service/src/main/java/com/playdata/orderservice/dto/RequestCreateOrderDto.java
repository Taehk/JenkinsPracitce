package com.playdata.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.playdata.orderservice.domain.Order;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RequestCreateOrderDto(
        @JsonProperty @Min(1) Long count,
        @JsonProperty @NotBlank String userId,
        @JsonProperty @NotBlank String productId
) {
    public Order toEntity(){
        return Order.builder()
                .count(count)
                .userId(userId)
                .productId(productId)
                .build();
    }
}
