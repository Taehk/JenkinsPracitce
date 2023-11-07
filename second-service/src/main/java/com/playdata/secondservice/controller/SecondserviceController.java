package com.playdata.secondservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
public class SecondserviceController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return"Hello, Second-service!";
    }
}
