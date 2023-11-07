package com.playdata.userService.dto;

import com.playdata.userService.domian.Order;
import com.playdata.userService.domian.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Valid
public class ResponseGetUserDTO {
    private String email;
    private String name;
    private String userId;
    private String uuid;
    
    // 필요하다면 구매내역을 같이 가져올 수 있도록 처리
    private Object orderList;

    public ResponseGetUserDTO(User user){
        email = user.getEmail();
        name = user.getName();
        userId = user.getUserId();
        uuid = user.getUuid();
        orderList = List.of();
    }
}
