package com.example.mscurrencyconverter.dto.response;

import com.example.mscurrencyconverter.dto.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

import java.util.Objects;

public class CurrencyResponse {
    @JsonProperty("Currency")
    private Currency currency;
    @JsonProperty("Amount")
    private BigDecimal amount;

    public CurrencyResponse() {
    }

    public CurrencyResponse(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyResponse that)) return false;

        if (currency != that.currency) return false;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        int result = currency != null ? currency.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CurrencyResponse{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
