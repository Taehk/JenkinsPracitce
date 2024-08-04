package com.playdata.discoveryClient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discovery-api")
public class DiscoveryCleintController {
    @RequestMapping(value = "/health-check", method = RequestMethod.GET)
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Discovey Clinet Server Health Check");
    }

}



