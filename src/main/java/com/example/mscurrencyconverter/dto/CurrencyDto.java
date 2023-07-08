package com.example.mscurrencyconverter.dto;

import com.example.mscurrencyconverter.dto.enums.Currency;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Objects;

public class CurrencyDto {
    private Currency currency;
    private BigDecimal value;

    public CurrencyDto(Currency currency, BigDecimal value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCode() {
        return currency;
    }

    public void setCode(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal amount) {
        this.value = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyDto that)) return false;

        if (!Objects.equals(currency, that.currency)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = currency != null ? currency.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String
    toString() {
        return "CurrencyDto{" +
                "code=" + currency +
                ", amount=" + value +
                '}';
    }
}
