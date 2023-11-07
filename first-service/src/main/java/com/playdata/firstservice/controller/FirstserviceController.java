package com.playdata.firstservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
@RequiredArgsConstructor
public class FirstserviceController {
    private final Environment env;
    @RequestMapping(value="/port-check", method = RequestMethod.GET)
    public String portCheck(){
        return env.getProperty("local.server.port") + "/" + env.getProperty("test.value");
            // server.port로 쓰면 port번호가 0으로 나타난다.
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello, First-service!";
    }

    @RequestMapping(value = "/header-check", method = RequestMethod.GET)
    public String headerCheck(@RequestHeader("f-req") String header){
        return header;
    }
}
