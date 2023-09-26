package com.paul.billing_system.service;

import com.paul.billing_system.repository.DrugOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class DrugOrderService {
    private final DrugOrderRepository drugOrderRepository;

    public DrugOrderService(DrugOrderRepository drugOrderRepository) {
        this.drugOrderRepository = drugOrderRepository;
    }
}
