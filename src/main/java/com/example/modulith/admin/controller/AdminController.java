package com.example.modulith.admin.controller;

import com.example.modulith.customer.service.CustomerService;
import com.example.modulith.dto.customer.CustomerResponse;
import com.example.modulith.shipment.service.ShipmentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final ShipmentService shipmentAPI;
    private final CustomerService customerAPI;

    @PostMapping("/shipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateShipmentStatus(
            @NotNull
            @PathVariable
            long id,
            @NotNull
            @RequestBody
            String status) {
        shipmentAPI.updateShipmentStatus(id, status);
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> getCustomerById(
            @NotNull
            @PathVariable
            long id) {
        return ResponseEntity.ofNullable(customerAPI.findCustomerById(id));
    }
}
