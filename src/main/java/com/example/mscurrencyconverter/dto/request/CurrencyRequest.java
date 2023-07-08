package com.example.mscurrencyconverter.dto.request;

import com.example.mscurrencyconverter.dto.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

import java.util.Objects;

public class CurrencyRequest {
    @JsonProperty("Currency")
    private Currency currency;
    //TODO: Check currency
    //TODO: Check amount
    @JsonProperty("Amount")
    private BigDecimal amount;
    @JsonProperty("ConvertTo")
    private Currency convertTo;

    public CurrencyRequest() {
    }

    public CurrencyRequest(Currency currency, BigDecimal amount, Currency convertTo) {
        this.currency = currency;
        this.amount = amount;
        this.convertTo = convertTo;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount( BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getConvertTo() {
        return convertTo;
    }

    public void setConvertTo(Currency  convertTo) {
        this.convertTo = convertTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyRequest that)) return false;

        if (currency != that.currency) return false;
        if (!Objects.equals(amount, that.amount)) return false;
        return convertTo == that.convertTo;
    }

    @Override
    public int hashCode() {
        int result = currency != null ? currency.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (convertTo != null ? convertTo.hashCode() : 0);
        return result;
    }
}
