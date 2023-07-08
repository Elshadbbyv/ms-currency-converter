package com.example.mscurrencyconverter.service;


import com.example.mscurrencyconverter.dto.CurrencyDto;
import com.example.mscurrencyconverter.dto.enums.Currency;
import com.example.mscurrencyconverter.dto.request.CurrencyRequest;
import com.example.mscurrencyconverter.dto.response.CurrencyResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class CurrencyConversionService {
    private final RestConversionService restConversionService;

    public CurrencyConversionService(RestConversionService restConversionService) {
        this.restConversionService = restConversionService;
    }

    public CurrencyResponse convertFromFirstValuteToSecondValute(CurrencyRequest request) throws JsonProcessingException {
        CurrencyDto firstCurrency = restConversionService.getDataFromMs(request).get(0);
        CurrencyDto convertedCurrency = restConversionService.getDataFromMs(request).get(1);
        BigDecimal newAmount = firstCurrency.getValue().divide(convertedCurrency.getValue(), RoundingMode.CEILING);
        return new CurrencyResponse(convertedCurrency.getCode(),newAmount.multiply(request.getAmount()));

    }
    public CurrencyDto getCurrencyFromRestConversionService(CurrencyRequest request) throws JsonProcessingException {
        return restConversionService.getDataFromMs(request).get(0);
    }
    public CurrencyDto getConvertToFromRestConversionService(CurrencyRequest request) throws JsonProcessingException {
        return restConversionService.getDataFromMs(request).get(1);
    }
    public CurrencyResponse calculateAmount(Currency convertTo,
                                            BigDecimal firstAmount,
                                            BigDecimal secondAmount,
                                            BigDecimal amount){
        if (firstAmount == BigDecimal.ONE) {
            return new CurrencyResponse(convertTo, BigDecimal.valueOf(amount.doubleValue()).divide(secondAmount,RoundingMode.CEILING));
        }

        return new CurrencyResponse(convertTo,amount.multiply(firstAmount.divide(secondAmount,RoundingMode.CEILING)));
    }




}
