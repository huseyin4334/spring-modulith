@ApplicationModule(
        allowedDependencies = {"shipment::service", "calculator::service", "dto"}
)
package com.example.modulith.customer;

import org.springframework.modulith.ApplicationModule;