package com.paul.billing_system.service;

import com.paul.billing_system.repository.DrugFormationRepository;
import org.springframework.stereotype.Service;

@Service
public class DrugFormationService {
    private final DrugFormationRepository drugFormationRepository;

    public DrugFormationService(DrugFormationRepository drugFormationRepository) {
        this.drugFormationRepository = drugFormationRepository;
    }
}
