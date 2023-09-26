package com.paul.billing_system.service;

import com.paul.billing_system.entity.DrugVendor;
import com.paul.billing_system.repository.DrugVendorRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugVendorService {
    private final DrugVendorRepository drugVendorRepository;

    public DrugVendorService(DrugVendorRepository drugVendorRepository) {
        this.drugVendorRepository = drugVendorRepository;
    }

    public List<DrugVendor> searchDrugVendor(String name, PageRequest pageRequest) {
        return drugVendorRepository.searchByName(name, pageRequest);
    }
}
