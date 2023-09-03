package com.paul.billing_system.controller;

import com.paul.billing_system.service.ServicesInfoServices;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicesController {
    private final ServicesInfoServices servicesInfoServices;

    public ServicesController(ServicesInfoServices servicesInfoServices) {
        this.servicesInfoServices = servicesInfoServices;
    }
}
