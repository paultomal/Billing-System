package com.paul.billing_system.service;

import com.paul.billing_system.repository.DrugStrengthRepository;
import org.springframework.stereotype.Service;

@Service
public class DrugStrengthService {
    private final DrugStrengthRepository drugStrengthRepository;

    public DrugStrengthService(DrugStrengthRepository drugStrengthRepository) {
        this.drugStrengthRepository = drugStrengthRepository;
    }
}
