package com.example.modulith.dto.customer;

public record CustomerResponse(Long id,
                               String firstName,
                               String lastName,
                               String country,
                               String phoneNumber,
                               String email) {
}
