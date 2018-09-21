package com.axel.testpay.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Transaction extends AbstractEntity {

    @JsonSetter("external_id")
    private String externalId;
    private Amount amount;
    @JsonSetter("description")
    private String description;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Transaction() {
    }
}
