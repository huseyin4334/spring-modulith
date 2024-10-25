package com.example.modulith.customer.listener;

import com.example.modulith.events.ShipmentCreateEvent;
import com.example.modulith.events.ShipmentStatusChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;


@Slf4j
public class ShipmentListener {

    @EventListener
    void onUpdateShipmentStatusEvent(ShipmentStatusChangeEvent event) {
        log.info("Changed status of shipment: {}", event.getShipmentId());
    }

    @EventListener
    void onShipmentCreateEvent(ShipmentCreateEvent event) {
        log.info("Created shipment: {}", event.getShipmentId());
    }
}
