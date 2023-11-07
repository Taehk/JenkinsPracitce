package com.playdata.userService.dto;

import com.playdata.userService.domian.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Valid
public class RequestLoginUserDTO {
    @Email
    String email;
    @Size(min = 8, max = 20, message = "비밀번호는 8~20글자")
    String pw;
    @NotNull
    String name;
    @NotNull
    String userId;


    // DTO 내에서 Entity를 생성하여 반환
    public User toEntitiy(){
        return User.builder()
                .email(email)
                .encPw(pw)
                .name(name)
                .userId(userId)
                .uuid(UUID.randomUUID().toString())
                .createAt(LocalDateTime.now())
                .build();
    }
}
