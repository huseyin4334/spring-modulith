package com.example.modulith.shipment.model;

import com.example.modulith.dto.model.DatabaseObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shipment")
@NoArgsConstructor
public class Shipment extends DatabaseObject<Long> {

    @Id
    @SequenceGenerator(name = "shipment_id_seq", sequenceName = "shipment_id_seq", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_id_seq")
    private Long id;
    private Long customerId;
    private double weight;
    private String addressFrom;
    private String addressTo;

    // This is postgres specific. It is used to define the enum type in the database.
    @Column(columnDefinition = "ENUM('NEW', 'IN_PROGRESS', 'DELIVERED') NOT NULL DEFAULT 'NEW'")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM) // This is required for PostgreSQL. Named_enum is the type code for enum types in PostgreSQL.
    private DeliveryStatus deliveryStatus;

    private double price;
}
