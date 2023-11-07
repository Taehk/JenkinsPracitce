package com.playdata.userService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.playdata.userService.domian.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseFindAllRecordDto(
        @JsonProperty("pw") String pw,
        @JsonProperty("email") String email,
        @JsonProperty("name") String name,
        @JsonProperty("userId") String userId,
        @JsonProperty("uuid") String uuid) {

    public ResponseFindAllRecordDto toDto(User user){
        return new ResponseFindAllRecordDto(user.getEncPw(),
                user.getEmail(),
                user.getName(),
                user.getUserId(),
                user.getUuid());
    }
}
