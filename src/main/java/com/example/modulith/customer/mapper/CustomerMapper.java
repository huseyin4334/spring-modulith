package com.example.modulith.customer.mapper;

import com.example.modulith.customer.model.Customer;
import com.example.modulith.dto.customer.CustomerRequest;
import com.example.modulith.dto.customer.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer mapToCustomer(CustomerRequest request);

    CustomerResponse mapToCustomerResponse(Customer customer);
}
