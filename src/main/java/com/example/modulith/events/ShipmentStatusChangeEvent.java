package com.example.modulith.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShipmentStatusChangeEvent {
    Long shipmentId;
}
