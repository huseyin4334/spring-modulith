package com.example.modulith.customer.model;

import com.example.modulith.dto.model.DatabaseObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer extends DatabaseObject<Long> {

    @Id
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String country;
}
