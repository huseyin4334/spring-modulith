package com.example.modulith.shipment.service.internal;

import com.example.modulith.dto.shipment.ShipmentRequest;
import com.example.modulith.shipment.mapper.ShipmentMapper;
import com.example.modulith.shipment.model.DeliveryStatus;
import com.example.modulith.shipment.model.Shipment;
import com.example.modulith.shipment.repository.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentInternalService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Transactional
    public Shipment createOrder(ShipmentRequest request, Long customerId) {

        Shipment shipment = ShipmentMapper.INSTANCE.mapToShipment(request);
        shipment.setCustomerId(customerId);
        shipment.setDeliveryStatus(DeliveryStatus.NEW);

        return shipmentRepository.save(shipment);
    }

    public List<Shipment> findOrdersByCustomerId(Long id) {
        return shipmentRepository.findByCustomerId(id);
    }

    @Transactional
    public void updateShipmentStatus(Long id, String status) throws EntityNotFoundException {
        Optional<Shipment> shipmentOpt = shipmentRepository.findById(id);
        if (shipmentOpt.isPresent()) {
            Shipment shipment = shipmentOpt.get();
            shipment.setDeliveryStatus(DeliveryStatus.valueOf(status));
            shipmentRepository.save(shipment);
        } else throw new EntityNotFoundException("Couldn't find shipment with id " + id);
    }
}
