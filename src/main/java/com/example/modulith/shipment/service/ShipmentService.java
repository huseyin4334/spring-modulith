package com.example.modulith.shipment.service;

import com.example.modulith.admin.controller.AdminController;
import com.example.modulith.calculator.repository.CurrencyRepository;
import com.example.modulith.calculator.service.CalculatorService;
import com.example.modulith.events.ShipmentCreateEvent;
import com.example.modulith.events.ShipmentStatusChangeEvent;
import com.example.modulith.dto.shipment.ShipmentRequest;
import com.example.modulith.dto.shipment.ShipmentResponse;
import com.example.modulith.shipment.mapper.ShipmentMapper;
import com.example.modulith.shipment.model.Shipment;
import com.example.modulith.shipment.service.internal.ShipmentInternalService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentInternalService shipmentInternalService;

    @Autowired
    private ApplicationEventPublisher events;

    public ShipmentResponse createOrder(ShipmentRequest request, Long customerId) {
        Shipment newShipment = shipmentInternalService.createOrder(request, customerId);
        events.publishEvent(new ShipmentCreateEvent(newShipment.getId()));
        return ShipmentMapper.INSTANCE.mapToShipmentResponse(newShipment);

    }

    public List<ShipmentResponse> findOrdersByCustomerId(Long id) {
        List<Shipment> shipments = shipmentInternalService.findOrdersByCustomerId(id);
        return shipments.stream().map(ShipmentMapper.INSTANCE::mapToShipmentResponse).toList();
    }

    public void updateShipmentStatus(Long id, String status) {
        shipmentInternalService.updateShipmentStatus(id, status);
        events.publishEvent(new ShipmentStatusChangeEvent(id));
    }
}
