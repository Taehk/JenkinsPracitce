package com.playdata.userService.domian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor  //JPA쪽에선 Default 생성자 필요
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String userId;
    private String encPw;

    private LocalDateTime createAt;
    // REST Api로 PK를 통해 조회한다면 EndPoint를 다른 기업에서 알았을 때
    // 우리 서비스의 회원이 얼만지 유추하는 등의 위험이 있을 수 있으므로
    // 따로 조회용 id를 만들어 관리한다.
        // DTO에서 엔티티를 생성할 때, UUID.randomUUID().toString()로 값을 만든다.
    private String uuid;
}
