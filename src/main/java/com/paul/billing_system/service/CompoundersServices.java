package com.paul.billing_system.service;

import com.paul.billing_system.dto.CompoundersChamberDTO;
import com.paul.billing_system.dto.CompoundersHospitalDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.repository.CompoundersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CompoundersServices {
    private final CompoundersRepository compoundersRepository;

    public CompoundersServices(CompoundersRepository compoundersRepository) {
        this.compoundersRepository = compoundersRepository;
    }

    //Hospital Compouder Services
    public Compounders saveHospitalCompounders(CompoundersHospitalDTO compoundersHospitalDTO) {
        Compounders compounders = new Compounders();
        compounders.setName(compoundersHospitalDTO.getName());
        compounders.setDesignation(compoundersHospitalDTO.getDesignation());
        return compoundersRepository.save(compounders);
    }

    public List<Compounders> getAllCompoundersOfHopitals() {
    return compoundersRepository.findAll();
    }

    public Compounders updateCompounderOfHospital(CompoundersHospitalDTO staffsDTO, Long id) {
        Optional<Compounders> staffs = compoundersRepository.findById(id);
        if (staffs.isPresent()){
            Compounders staffs1 = new Compounders();
            staffs1.setName(staffsDTO.getName());
            staffs1.setDesignation(staffsDTO.getDesignation());
            return compoundersRepository.save(staffs1);
        }
        return new Compounders();
    }


    //Chamber Compounder Services
    public Compounders saveChamberCoumpounder(CompoundersChamberDTO compoundersChamberDTO) {
        Compounders compounders = new Compounders();
        compounders.setName(compoundersChamberDTO.getName());
        compounders.setDepartment(compoundersChamberDTO.getDepartment());
        return compoundersRepository.save(compounders);
    }

    public List<Compounders> getAllCompoundersOfChamber() {
        return compoundersRepository.findAll();
    }

    public Compounders updateCompounderOfChamber(CompoundersChamberDTO compoundersChamberDTO, Long id) {
        Optional<Compounders> compounders = compoundersRepository.findById(id);
        if (compounders.isPresent()){
            Compounders compounders1 = new Compounders();
            compounders1.setName(compoundersChamberDTO.getName());
            compounders1.setDepartment(compoundersChamberDTO.getDepartment());
            return compoundersRepository.save(compounders1);
        }
        return new Compounders();
    }

    public Compounders getCompounderById(@PathVariable Long id){
        Optional<Compounders> compounders = compoundersRepository.findById(id);
        if (compounders.isPresent())
            return compounders.get();
        return new Compounders();
    }
}
