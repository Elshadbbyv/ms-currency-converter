package com.example.mscurrencyconverter.service;

import com.example.mscurrencyconverter.config.AppConfig;
import com.example.mscurrencyconverter.dto.CurrencyDto;
import com.example.mscurrencyconverter.dto.request.CurrencyRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RestConversionService {
    private final AppConfig appConfig;

    private final ObjectMapper objectMapper;

    public RestConversionService(AppConfig appConfig, ObjectMapper objectMapper) {
        this.appConfig = appConfig;
        this.objectMapper = objectMapper;
    }
    public List<CurrencyDto> getDataFromMs(CurrencyRequest currencyRequest) throws JsonProcessingException {
        ResponseEntity<String> response = (appConfig.restTemplate()
                .postForEntity("http://localhost:8000/convert/", currencyRequest,String.class));

        CurrencyDto[] currencyDtos = objectMapper.readValue(response.getBody(), CurrencyDto[].class);


        return Arrays.asList(currencyDtos);

    }
}
