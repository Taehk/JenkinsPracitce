package com.playdata.userService.domian;

import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Builder@ToString
public class Order {
    private Long id;

    private String orderId;
    private LocalDateTime createAt;

    private Long count;
    private String userId;
    private String productId;
}
