package com.playdata.itmeservice.dto;


import com.playdata.itmeservice.domain.Item;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Valid
public class RequestCreateItemDto{
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]+")
    @NotBlank(message = "상품 명은 반드시 등록되어야 합니다.")
    private String productName;
    @NotNull
    private Long stock;
    @NotNull
    private Long pricePerItem;

    public Item toEntity(){
        return Item.builder()
                .productId(UUID.randomUUID().toString())
                .productName(productName)
                .pricePerItem(pricePerItem)
                .stock(stock)
                .build();
    }
}
