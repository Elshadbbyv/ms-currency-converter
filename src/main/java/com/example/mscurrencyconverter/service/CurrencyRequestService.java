package com.example.mscurrencyconverter.service;

import com.example.mscurrencyconverter.dto.CurrencyDto;
import com.example.mscurrencyconverter.dto.enums.Currency;
import com.example.mscurrencyconverter.dto.request.CurrencyRequest;
import com.example.mscurrencyconverter.dto.response.CurrencyResponse;
import com.example.mscurrencyconverter.exception.AmountNegativeException;
import com.example.mscurrencyconverter.exception.CurrencyNotFoundException;
import com.example.mscurrencyconverter.exception.RestTemplateErrorHandler;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class CurrencyRequestService {
    private final RestConversionService restConversionService;
    private final CurrencyConversionService conversionService;
    private final RestTemplateErrorHandler errorHandler;


    public CurrencyRequestService(RestConversionService restConversionService, CurrencyConversionService conversionService,
                                  RestTemplateErrorHandler errorHandler) {
        this.restConversionService = restConversionService;
        this.conversionService = conversionService;
        this.errorHandler = errorHandler;

    }



    public CurrencyResponse checkRequestFromController(CurrencyRequest request) throws IOException {
        if (!(request.getAmount().compareTo(BigDecimal.ZERO)>-1)) {
            throw new AmountNegativeException("Amount cannot be negative!");
        }
        if (request.getCurrency().equals(Currency.AZN)&&request.getConvertTo().equals(Currency.AZN)){
            return conversionService.calculateAmount(Currency.AZN,BigDecimal.ONE,BigDecimal.ONE,request.getAmount());
        }
        if (request.getCurrency().equals(Currency.AZN)) {
            CurrencyDto currencyDto = conversionService.getConvertToFromRestConversionService(request);
            return conversionService.calculateAmount(currencyDto.getCode(),BigDecimal.ONE, currencyDto.getValue(),request.getAmount());
        }
        if (request.getConvertTo().equals(Currency.AZN)){
            CurrencyDto currencyDto = conversionService.getCurrencyFromRestConversionService(request);
            return conversionService.calculateAmount(Currency.AZN,currencyDto.getValue(), BigDecimal.ONE,request.getAmount() );
        }
        CurrencyDto currencyDto = conversionService.getCurrencyFromRestConversionService(request);
        CurrencyDto convertToDto = conversionService.getConvertToFromRestConversionService(request);

        return conversionService.calculateAmount(convertToDto.getCode(),currencyDto.getValue(), convertToDto.getValue(),request.getAmount());
    }
}
