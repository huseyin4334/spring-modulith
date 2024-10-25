package com.example.modulith.calculator.repository;

import com.example.modulith.calculator.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
