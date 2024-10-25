package com.example.modulith.customer.service;

import com.example.modulith.customer.mapper.CustomerMapper;
import com.example.modulith.customer.model.Customer;
import com.example.modulith.customer.repository.CustomerRepository;
import com.example.modulith.dto.customer.CustomerRequest;
import com.example.modulith.dto.customer.CustomerResponse;
import com.example.modulith.shipment.service.internal.ShipmentInternalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository repository;

    public CustomerResponse saveCustomer(CustomerRequest request) {
        Customer customer = CustomerMapper.INSTANCE.mapToCustomer(request);
        return CustomerMapper.INSTANCE.mapToCustomerResponse(repository.save(customer));
    }

    public CustomerResponse findCustomerById(Long id) {
        Optional<Customer> customerOpt = repository.findById(id);
        if (customerOpt.isPresent()) {
            return CustomerMapper.INSTANCE.mapToCustomerResponse(customerOpt.get());
        } else throw new EntityNotFoundException("Couldn't find customer with id " + id);
    }
}
