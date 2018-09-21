package com.axel.testpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractEntity {
    @JsonIgnore
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
