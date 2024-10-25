package com.example.modulith.shipment.util;

import com.example.modulith.shipment.model.DeliveryStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<DeliveryStatus, String> {

    @Override
    public String convertToDatabaseColumn(DeliveryStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public DeliveryStatus convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;

        return Stream.of(DeliveryStatus.values())
                .filter(n -> n.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
