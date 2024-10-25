package com.example.modulith.dto.shipment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShipmentResponse {

    Long id;
    Long customerId;
    double weight;
    String addressFrom;
    String addressTo;
    double price;
    String deliveryStatus;
}
