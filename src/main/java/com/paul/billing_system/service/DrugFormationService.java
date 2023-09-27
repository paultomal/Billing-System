package com.paul.billing_system.service;

import com.paul.billing_system.entity.DrugFormation;
import com.paul.billing_system.repository.DrugFormationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugFormationService {
    private final DrugFormationRepository drugFormationRepository;

    public DrugFormationService(DrugFormationRepository drugFormationRepository) {
        this.drugFormationRepository = drugFormationRepository;
    }

    public List<DrugFormation> searchDrugFormation(String name, PageRequest pageRequest) {
        return drugFormationRepository.searchByName(name, pageRequest);
    }
}
