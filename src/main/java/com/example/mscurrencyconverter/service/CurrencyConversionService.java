package com.example.mscurrencyconverter.service;
import com.example.mscurrencyconverter.config.RestConversionService;
import com.example.mscurrencyconverter.dto.CurrencyDto;
import com.example.mscurrencyconverter.dto.enums.Currency;
import com.example.mscurrencyconverter.dto.request.CurrencyRequest;
import com.example.mscurrencyconverter.dto.response.CurrencyResponse;
import com.example.mscurrencyconverter.exception.AmountNegativeException;
import com.example.mscurrencyconverter.model.Valute;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CurrencyConversionService {
    private final RestConversionService restConversionService;

    public CurrencyConversionService(RestConversionService restConversionService) {
        this.restConversionService = restConversionService;
    }
    public CurrencyDto getCurrencyField(CurrencyRequest request) throws IOException {
        if (request.getCurrency() == Currency.AZN) {
            return new CurrencyDto(Currency.AZN, BigDecimal.ONE);
        }

        List<Valute> firstValute = restConversionService.getDataOtherMs().getValType().stream()
                .flatMap(valType -> valType.getValute().stream())
                .filter(valute -> valute.getCode().equals(Currency.getName(request.getCurrency())))
                .toList();

        return new CurrencyDto(Currency.valueOf(firstValute.get(0).getCode()), firstValute.get(0).getValue());
    }
    public CurrencyDto getConvertToField(CurrencyRequest request) throws IOException {
        if (request.getConvertTo().equals(Currency.AZN)) {
            return new CurrencyDto(Currency.AZN, BigDecimal.ONE);
        }
        List<Valute> secondValute = restConversionService.getDataOtherMs().getValType().stream()
                .flatMap(valType -> valType.getValute().stream())
                .filter(valute -> valute.getCode().equals(Currency.getName(request.getConvertTo())))
                .toList();
        return new CurrencyDto(Currency.valueOf(secondValute.get(0).getCode()), secondValute.get(0).getValue());
    }
    public CurrencyResponse checkRequestFromController(CurrencyRequest request) throws IOException {
        if (request.getAmount().compareTo(BigDecimal.ZERO)<0 ) {
            throw new AmountNegativeException("Amount cannot be Negative");
        }
        CurrencyDto currencyDto = getCurrencyField(request);
        CurrencyDto convertToDto = getConvertToField(request);
        return new CurrencyResponse(convertToDto.getCode(),
                request.getAmount().multiply(currencyDto.getValue()
                        .divide(convertToDto.getValue(), RoundingMode.CEILING)));
    }


}
