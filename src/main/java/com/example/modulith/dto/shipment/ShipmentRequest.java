package com.example.modulith.dto.shipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShipmentRequest {

    @NotNull
    double weight;
    @NotBlank
    String addressFrom;
    @NotBlank
    String addressTo;
    @NotNull
    double price;
}
