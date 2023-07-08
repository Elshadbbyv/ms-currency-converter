package com.example.mscurrencyconverter;

import com.example.mscurrencyconverter.dto.enums.Currency;
import com.example.mscurrencyconverter.dto.request.CurrencyRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class MsCurrencyConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCurrencyConverterApplication.class, args);

    }

}
