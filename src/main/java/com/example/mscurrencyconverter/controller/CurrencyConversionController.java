package com.example.mscurrencyconverter.controller;

import com.example.mscurrencyconverter.dto.request.CurrencyRequest;
import com.example.mscurrencyconverter.dto.response.CurrencyResponse;
import com.example.mscurrencyconverter.service.CurrencyConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/currency")
public class CurrencyConversionController {
    private final CurrencyConversionService currencyConversionService;

    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }
    @PostMapping
    public ResponseEntity<CurrencyResponse> get(@RequestBody CurrencyRequest request) throws IOException {
        return ResponseEntity.ok(currencyConversionService.checkRequestFromController(request));
    }
}
