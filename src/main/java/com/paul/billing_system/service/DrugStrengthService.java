package com.paul.billing_system.service;

import com.paul.billing_system.entity.DrugStrength;
import com.paul.billing_system.repository.DrugStrengthRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugStrengthService {
    private final DrugStrengthRepository drugStrengthRepository;

    public DrugStrengthService(DrugStrengthRepository drugStrengthRepository) {
        this.drugStrengthRepository = drugStrengthRepository;
    }

    public List<DrugStrength> searchDrugStrength(String name, PageRequest pageRequest) {
        return drugStrengthRepository.searchByName(name, pageRequest);
    }
}
