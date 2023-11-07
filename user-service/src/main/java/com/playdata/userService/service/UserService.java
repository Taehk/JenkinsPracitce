package com.playdata.userService.service;

import com.playdata.userService.domian.Order;
import com.playdata.userService.domian.User;
import com.playdata.userService.dto.*;
import com.playdata.userService.exception.UserNotFoundException;
import com.playdata.userService.feignclient.OrderFeignClient;
import com.playdata.userService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrderFeignClient orderFeignClient;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    public void createUser(RequestCreateUserDTO userDto){
        // dto -> entity 변경
        userRepository.save(userDto.toEntitiy());
    }

    // 회원가입 (레코드 타입)
    public void createRecord(RequestCreateRecordDto recordDto){
        userRepository.save(recordDto.toEntity());
    }

    // 회원 조회
    public ResponseGetUserDTO getUser(String uuid){
        return new ResponseGetUserDTO(
                userRepository.findUserByUuid(uuid)
                        .orElseThrow(UserNotFoundException::new)
                );
    }

    // 전체 회원 조회 (엔티티 리스트 반환)
    public List<ResponseFindAllRecordDto> getUserList(){
        return userRepository
                .findAll().stream()
                .map(user ->
                        new ResponseFindAllRecordDto(
                                user.getEncPw(),
                                user.getEmail(),
                                user.getName(),
                                user.getUserId(),
                                user.getUuid()
                        )
                ).toList();
    }

    // 회원 아이디를 통해 주문 내역 표시
    public ResponseGetUserDTO findUserOderList(String userId){
        // 1. 특정 유저 아이디 가져오기
        User user = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));
        // 2. Feign 클라이언트를 이용해서 특정 유저의 주문 목록 가져오기
        List<Order> orderList = orderFeignClient.getOrderListByUserId(userId);
        log.info("주문 내역 확인하기 {}",orderList);
        // 3. 세터로 합쳐주기
        ResponseGetUserDTO userDto = ResponseGetUserDTO.builder()
                .userId(userId)
                .email(user.getEmail())
                .name(user.getName())
                .uuid(user.getUuid())
                .orderList(orderList)
                .build();
        // 4. 합쳐준걸 리턴
        return userDto;
    }
}
