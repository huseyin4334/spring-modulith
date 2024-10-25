@ApplicationModule(
        allowedDependencies = {"shipment::service", "customer::service", "dto"}
)
package com.example.modulith.admin;

import org.springframework.modulith.ApplicationModule;