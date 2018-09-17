package com.axel.testpay.model;

public class Amount extends AbstractEntity {

    private String value;

    private String currency;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Amount() {
    }
}
