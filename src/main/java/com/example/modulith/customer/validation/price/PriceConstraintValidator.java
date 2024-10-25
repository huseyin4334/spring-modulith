package com.example.modulith.customer.validation.price;

import com.example.modulith.dto.calculator.CalculatorRequest;
import com.example.modulith.dto.shipment.ShipmentRequest;
import com.example.modulith.calculator.service.CalculatorService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceConstraintValidator implements ConstraintValidator<CorrectShipmentPrice, ShipmentRequest> {

    private final CalculatorService calculatorAPI;

    @Override
    public boolean isValid(ShipmentRequest request, ConstraintValidatorContext constraintValidatorContext) {
        double price;
        if (request == null) return true;

        price = calculatorAPI.calculatePrice(
                new CalculatorRequest(request.getWeight(), request.getAddressFrom(), request.getAddressTo()));
        return request.getPrice() == price;
    }
}
