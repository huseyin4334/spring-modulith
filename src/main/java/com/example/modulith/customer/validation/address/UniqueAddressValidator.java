package com.example.modulith.customer.validation.address;


import com.example.modulith.dto.shipment.ShipmentRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class UniqueAddressValidator implements ConstraintValidator<UniqueAddress, ShipmentRequest> {

    @Override
    public boolean isValid(ShipmentRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request == null) return true;
        return !Objects.equals(request.getAddressTo(), request.getAddressFrom());
    }
}

