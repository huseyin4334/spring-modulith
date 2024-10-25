package com.example.modulith.shipment.model;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    DELIVERED("DELIVERED");

    private final String value;

    DeliveryStatus(String value) {
        this.value = value;
    }
}
