package com.example.modulith.customer.controller;

import com.example.modulith.customer.service.CustomerService;
import com.example.modulith.customer.validation.address.UniqueAddress;
import com.example.modulith.customer.validation.customer.ExistingCustomer;
import com.example.modulith.calculator.service.CalculatorService;
import com.example.modulith.customer.validation.email.UniqueEmail;
import com.example.modulith.customer.validation.phone.CorrectPhoneNumber;
import com.example.modulith.customer.validation.phone.UniquePhoneNumber;
import com.example.modulith.customer.validation.price.CorrectShipmentPrice;
import com.example.modulith.dto.calculator.CalculatorRequest;
import com.example.modulith.dto.customer.CustomerRequest;
import com.example.modulith.dto.customer.CustomerResponse;
import com.example.modulith.dto.shipment.ShipmentRequest;
import com.example.modulith.dto.shipment.ShipmentResponse;
import com.example.modulith.shipment.service.ShipmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final ShipmentService shipmentAPI;
    private final CalculatorService calculatorAPI;
    private final CustomerService service;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResponse> createCustomer(
            @NotNull
            @Valid
            @RequestBody
            @CorrectPhoneNumber
            @UniquePhoneNumber
            @UniqueEmail
            CustomerRequest request) {
        return ResponseEntity.ofNullable(service.saveCustomer(request));
    }

    @GetMapping("/customers/{id}/shipments")
    @ResponseStatus(HttpStatus.OK)
    public List<ShipmentResponse> getAllOrdersForCustomer(
            @NotNull
            @PathVariable
            @ExistingCustomer
            long id) {
        return shipmentAPI.findOrdersByCustomerId(id);
    }

    @PostMapping("/quote")
    public ResponseEntity<Double> calculatePrice(
            @NotNull
            @Valid
            @RequestBody
            CalculatorRequest request) {
        Double price = calculatorAPI.calculatePrice(request);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @PostMapping("/customers/{id}/shipment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ShipmentResponse> createShipmentOrder(
            @NotNull
            @Valid
            @RequestBody
            @CorrectShipmentPrice
            @UniqueAddress
            ShipmentRequest request,
            @NotNull
            @PathVariable
            @ExistingCustomer
            long id
    ) {
        return ResponseEntity.ofNullable(shipmentAPI.createOrder(request, id));
    }


}
