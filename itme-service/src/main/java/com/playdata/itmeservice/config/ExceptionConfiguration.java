package com.playdata.itmeservice.config;

import com.playdata.itmeservice.util.CustomFeignException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfiguration {
    @Bean
    public void CustomException () {
        new CustomFeignException();
    }
}
