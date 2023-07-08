package com.example.mscurrencyconverter.config;

import com.example.mscurrencyconverter.exception.RestTemplateErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration(proxyBeanMethods = false)
public class AppConfig {
    private final RestTemplateErrorHandler restTemplateErrorHandler;

    public AppConfig(RestTemplateErrorHandler restTemplateErrorHandler) {
        this.restTemplateErrorHandler = restTemplateErrorHandler;
    }

    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplateBuilder()
                .errorHandler(restTemplateErrorHandler)
                .build();
    }




}