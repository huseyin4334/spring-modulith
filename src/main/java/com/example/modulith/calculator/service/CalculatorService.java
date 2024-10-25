package com.example.modulith.calculator.service;

import com.example.modulith.dto.calculator.CalculatorRequest;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double calculatePrice(CalculatorRequest request) {

        //that's pure placeholder for the simplicity sake :)
        return 10.0 * request.weight();
    }
}
