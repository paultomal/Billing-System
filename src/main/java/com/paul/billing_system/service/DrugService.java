package com.paul.billing_system.service;

import com.paul.billing_system.dto.DrugDTO;
import com.paul.billing_system.entity.Drug;
import com.paul.billing_system.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    private final DrugRepository drugRepository;
    private final DrugFormationRepository drugFormationRepository;
    private final DrugStrengthRepository drugStrengthRepository;
    private final GenericRepository genericRepository;
    private final DrugVendorRepository drugVendorRepository;

    public DrugService(DrugRepository drugRepository, DrugFormationRepository drugFormationRepository, DrugStrengthRepository drugStrengthRepository, GenericRepository genericRepository, DrugVendorRepository drugVendorRepository) {
        this.drugRepository = drugRepository;
        this.drugFormationRepository = drugFormationRepository;
        this.drugStrengthRepository = drugStrengthRepository;
        this.genericRepository = genericRepository;
        this.drugVendorRepository = drugVendorRepository;
    }

    @Transactional
    public DrugDTO addDrug(DrugDTO drugDTO) {
        Drug drug = new Drug();
        drug.setBrandName(drugDTO.getBrandName());
        drug.setPrice(drugDTO.getPrice());
        drug.setGeneric(genericRepository.findByName(drugDTO.getGenericName()));
        drug.setFormation(drugFormationRepository.findByName(drugDTO.getFormationName()));
        drug.setVendor(drugVendorRepository.findByName(drugDTO.getVendorName()));
        drug.setStrength(drugStrengthRepository.findByName(drugDTO.getStrengthName()));

        return DrugDTO.form(drugRepository.save(drug));
    }

    public List<DrugDTO> getAllDrugs(PageRequest pageRequest) {
        return drugRepository.findAll(pageRequest).getContent()
                .stream()
                .map(DrugDTO::form)
                .toList();
    }

    public DrugDTO getDrugById(Long id) {
        Optional<Drug> drug = drugRepository.findById(id);

        return drug.map(DrugDTO::form).orElse(null);
    }

    public DrugDTO updateDrug(Long id, DrugDTO drugDTO) {
        Optional<Drug> drug = drugRepository.findById(id);

        if(drug.isPresent()) {
            drug.get().setBrandName(drugDTO.getBrandName());
            drug.get().setPrice(drugDTO.getPrice());
            drug.get().setGeneric(genericRepository.findByName(drugDTO.getGenericName()));
            drug.get().setFormation(drugFormationRepository.findByName(drugDTO.getFormationName()));
            drug.get().setVendor(drugVendorRepository.findByName(drugDTO.getVendorName()));
            drug.get().setStrength(drugStrengthRepository.findByName(drugDTO.getStrengthName()));

            return DrugDTO.form(drugRepository.save(drug.get()));
        }

        return null;
    }

    public String deleteDrug(Long id) {
        if(drugRepository.findById(id).isPresent()) {
            drugRepository.deleteById(id);

            return "Drug deleted of ID: " + id;
        }

        return "Drug doesn't exist of ID: " + id;
    }

    public List<DrugDTO> searchDrugByBrandName(String name, PageRequest pageRequest) {
        return drugRepository.searchByName(name, pageRequest)
                .stream()
                .map(DrugDTO::form)
                .toList();
    }

    public List<DrugDTO> searchDrugByVendor(String name, PageRequest pageRequest) {
        return drugRepository.searchByVendor(name, pageRequest)
                .stream()
                .map(DrugDTO::form)
                .toList();
    }

    public List<DrugDTO> searchDrugByGeneric(String name, PageRequest pageRequest) {
        return drugRepository.searchByGeneric(name, pageRequest)
                .stream()
                .map(DrugDTO::form)
                .toList();
    }

    public List<DrugDTO> searchDrugByFormation(String name, PageRequest pageRequest) {
        return drugRepository.searchByFormation(name, pageRequest)
                .stream()
                .map(DrugDTO::form)
                .toList();
    }

    public List<DrugDTO> searchDrugByStrength(String name, PageRequest pageRequest) {
        return drugRepository.searchByStrength(name, pageRequest)
                .stream()
                .map(DrugDTO::form)
                .toList();
    }
}
