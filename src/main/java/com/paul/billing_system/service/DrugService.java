package com.paul.billing_system.service;

import com.paul.billing_system.dto.DrugDTO;
import com.paul.billing_system.entity.Drug;
import com.paul.billing_system.repository.DrugRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    private final DrugRepository drugRepository;

    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public DrugDTO addDrug(DrugDTO drugDTO) {
        Drug drug = new Drug();
        drug.setBrandName(drugDTO.getBrandName());

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

    public List<DrugDTO> searchDrug(String name, PageRequest pageRequest) {
        return drugRepository.searchByName(name, pageRequest)
                .stream()
                .map(DrugDTO::form)
                .toList();
    }
}
