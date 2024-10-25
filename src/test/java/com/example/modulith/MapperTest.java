package com.example.modulith;

import com.example.modulith.dto.shipment.ShipmentResponse;
import com.example.modulith.shipment.mapper.DeliveryStatusMapper;
import com.example.modulith.shipment.mapper.ShipmentMapper;
import com.example.modulith.shipment.model.DeliveryStatus;
import com.example.modulith.shipment.model.Shipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ShipmentMapper.class, DeliveryStatusMapper.class})
public class MapperTest {

    @Autowired
    ShipmentMapper shipmentMapper;

    @Test
    void shouldMapToShipmentResponse() {

        Shipment shipment = new Shipment(
                1L, 3L, 23.5,
                "London, Rose Lane, 2, 1A, 12345",
                "London, Holland Park, 20, 17, 15577",
                DeliveryStatus.NEW, 345.0);

        ShipmentResponse response = shipmentMapper.mapToShipmentResponse(shipment);
        assertEquals(shipment.getWeight(), response.getWeight());

    }

}
