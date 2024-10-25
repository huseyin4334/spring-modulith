package com.example.modulith.shipment.mapper;

import com.example.modulith.shipment.model.DeliveryStatus;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface DeliveryStatusMapper {
    String mapToDeliveryStatus(DeliveryStatus deliveryStatus);
    DeliveryStatus mapToDeliveryStatus(String deliveryStatus);
}
