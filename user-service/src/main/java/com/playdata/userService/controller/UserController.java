package com.playdata.userService.controller;

import com.playdata.userService.dto.RequestCreateRecordDto;
import com.playdata.userService.dto.RequestCreateUserDTO;
import com.playdata.userService.dto.ResponseFindAllRecordDto;
import com.playdata.userService.dto.ResponseGetUserDTO;
import com.playdata.userService.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/health-check", method = RequestMethod.GET)
    public String healthCheck(){
        return "USER-SERVICE is available";
    }

    // 회원가입
        // 일반 DTO
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> creatUser(@Valid @RequestBody RequestCreateUserDTO userDto){
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
        // Record타입 DTO
    @RequestMapping(value = "/records", method = RequestMethod.POST)
    public ResponseEntity<String> createRecord(@Valid @RequestBody RequestCreateRecordDto recordDto){
        userService.createRecord(recordDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    // 특정 UUID를 가진 회원 조회
    @RequestMapping(value = "/users/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<ResponseGetUserDTO> getUser(@PathVariable("uuid") String uuid){
        return ResponseEntity.ok(userService.getUser(uuid));
    }

    // 전체 회원 조회
    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseFindAllRecordDto>> findUserList(){
        return ResponseEntity.ok(userService.getUserList());
    }

    @RequestMapping(value = "/users/{userId}/orders")
    public ResponseEntity<ResponseGetUserDTO> getOrderList(@PathVariable String userId){
        return ResponseEntity.ok(userService.findUserOderList(userId));
    }
}
