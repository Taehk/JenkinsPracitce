package com.playdata.userService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.playdata.userService.domian.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

public record RequestCreateRecordDto(
        @JsonProperty("pw")
        @Size(min = 8, max = 20, message = "비밀번호는 8~20글자")
            String pw,
        @JsonProperty("email") @Email String email,
        @JsonProperty("name") @NotNull String name,
        @JsonProperty("userId") @NotNull String userId) {

    static String uuid = UUID.randomUUID().toString();

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .encPw(new BCryptPasswordEncoder().encode(this.pw))
                .name(name)
                .userId(userId)
                .uuid(uuid)
                .createAt(LocalDateTime.now())
                .build();
    }
}
