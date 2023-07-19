package com.example.mscurrencyconverter.dto.enums;

public enum Currency {
    USD,AZN,TRY,EUR,RUB;

    public static String getName(Currency currency){
        return currency.name();

    }
}
