package com.paul.billing_system.service;

import com.paul.billing_system.repository.ServicesRepository;
import org.springframework.stereotype.Service;

@Service
public class ServicesInfoServices {
    private final ServicesRepository servicesRepository;

    public ServicesInfoServices(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }
}
