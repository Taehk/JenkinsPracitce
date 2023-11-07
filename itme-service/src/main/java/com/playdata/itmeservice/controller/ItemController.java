package com.playdata.itmeservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.playdata.itmeservice.domain.Item;
import com.playdata.itmeservice.dto.RequestCreateItemDto;
import com.playdata.itmeservice.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item-service")
public class ItemController {
    private final ItemService itemService;

    @RequestMapping(value = "/health-check", method = RequestMethod.GET)
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Item-Service 등록 완료!");
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@Valid @RequestBody RequestCreateItemDto itemDto)
        throws JsonProcessingException {
        itemService.publishCreateItemMessage(itemDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("등록 완료");
    }

    @RequestMapping(value = "/items/all", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> findAllItem(){
        return ResponseEntity.ok(itemService.findAllItem());
    }

    @RequestMapping(value = "/items/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<Item> findItemByUuid(@PathVariable("uuid") String uuid){
        return ResponseEntity.ok(itemService.findItemByUuid(uuid));
    }

    @RequestMapping(value = "/items/{productId}/orders", method = RequestMethod.GET)
    public ResponseEntity<?> findItemInfoByProductId (@PathVariable String productId){
        return  ResponseEntity.ok(itemService.findItemInfoByProductId(productId));
    }

    @RequestMapping(value = "items/{message}/message")
    public ResponseEntity<?> publishTestMessage(@PathVariable String message){
        itemService.publishTestMessage(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}