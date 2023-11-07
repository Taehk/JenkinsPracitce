package com.playdata.orderservice.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Table(name = "orders")  @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String orderId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;

    @PrePersist
    public void uuid(){
        orderId = UUID.randomUUID().toString();
    }

    private Long count;


    private String userId;
    private String productId;
}
