package com.example.modulith.calculator.model;

import com.example.modulith.dto.model.DatabaseObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "currency")
public class Currency extends DatabaseObject<Long> {


    @Id
    @SequenceGenerator(name = "currency_id_seq", sequenceName = "currency_id_seq", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_id_seq")
    private Long id;

    private String name;
    private int rate;


}
