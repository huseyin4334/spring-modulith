package com.example.modulith.shipment.mapper;

import com.example.modulith.dto.shipment.ShipmentRequest;
import com.example.modulith.dto.shipment.ShipmentResponse;
import com.example.modulith.shipment.model.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = DeliveryStatusMapper.class)
public interface ShipmentMapper {
    ShipmentMapper INSTANCE = Mappers.getMapper(ShipmentMapper.class);

    Shipment mapToShipment(ShipmentRequest shipmentRequest);

    ShipmentResponse mapToShipmentResponse(Shipment shipment);
}
