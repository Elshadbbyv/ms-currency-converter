package com.example.mscurrencyconverter.controller;

import com.example.mscurrencyconverter.config.AppConfig;
import com.example.mscurrencyconverter.dto.CurrencyDto;
import com.example.mscurrencyconverter.dto.request.CurrencyRequest;
import com.example.mscurrencyconverter.dto.response.CurrencyResponse;
import com.example.mscurrencyconverter.service.CurrencyConversionService;
import com.example.mscurrencyconverter.service.CurrencyRequestService;
import com.example.mscurrencyconverter.service.RestConversionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.util.*;

@RestController
public class CurrencyConversionController {
    private final CurrencyConversionService currencyConversionService;
    private final RestConversionService restConversionService;
    private final CurrencyRequestService currencyRequestService;

    public CurrencyConversionController(CurrencyConversionService currencyConversionService,
                                        RestConversionService restConversionService,
                                        CurrencyRequestService currencyRequestService) {
        this.currencyConversionService = currencyConversionService;
        this.restConversionService = restConversionService;
        this.currencyRequestService = currencyRequestService;
    }



//    @GetMapping("/getAll")
//    public ResponseEntity<CurrencyResponse> convertFromCurrency(@RequestBody CurrencyRequest request) throws JsonProcessingException {
//        return ResponseEntity.ok(service.convertFromFirstValuteToSecondValute(request));
//    }
    @PostMapping("/get")
    public CurrencyResponse getDataFromMs(@RequestBody CurrencyRequest currencyRequest) throws RestClientException, JsonProcessingException {
        return currencyConversionService.convertFromFirstValuteToSecondValute(currencyRequest);
    }
    @PostMapping("/getAll")
    public CurrencyResponse getDataFromRequest(@RequestBody CurrencyRequest currencyRequest) throws RestClientException, IOException {
        return currencyRequestService.checkRequestFromController(currencyRequest);
    }


}
