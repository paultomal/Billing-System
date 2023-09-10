package com.paul.billing_system.service;

import com.paul.billing_system.dto.CompoundersChamberDTO;
import com.paul.billing_system.dto.CompoundersHospitalDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.repository.CompoundersRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CompoundersServices {
    private final CompoundersRepository compoundersRepository;
    private final OrganizationRepository organizationRepository;

    public CompoundersServices(CompoundersRepository compoundersRepository, OrganizationRepository organizationRepository) {
        this.compoundersRepository = compoundersRepository;
        this.organizationRepository = organizationRepository;
    }

    //Hospital Compouder Services
    public Compounders saveHospitalCompounders(Long id, CompoundersHospitalDTO compoundersHospitalDTO) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Compounders compounders = new Compounders();
        if (organization.isPresent()) {
            if (organization.get().getType().equals(OrganizationTypes.HOSPITAL)) {
                compounders.setName(compoundersHospitalDTO.getName());
                compounders.setDesignation(compoundersHospitalDTO.getDesignation());
                compoundersRepository.save(compounders);
                organization.get().getCompounders().add(compounders);
                organizationRepository.save(organization.get());
            }
        }

        return compounders;
    }

    public List<Compounders> getAllCompoundersOfHopitals() {
        return compoundersRepository.findAll();
    }

    public Compounders updateCompounderOfHospital(CompoundersHospitalDTO compoundersHospitalDTO, Long id) {
        Optional<Compounders> compounders = compoundersRepository.findById(id);
        if (compounders.isPresent()) {
            Compounders compounders1 = new Compounders();
            compounders1.setName(compoundersHospitalDTO.getName());
            compounders1.setDesignation(compoundersHospitalDTO.getDesignation());
            return compoundersRepository.save(compounders1);
        }
        return new Compounders();
    }


    //Chamber Compounder Services
    public Compounders saveChamberCoumpounder(Long id, CompoundersChamberDTO compoundersChamberDTO) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Compounders compounders = new Compounders();
        if (organization.isPresent()) {
            if (organization.get().getType().equals(OrganizationTypes.CHAMBER)) {
                compounders.setName(compoundersChamberDTO.getName());
                compounders.setDepartment(compoundersChamberDTO.getDepartment());
                compoundersRepository.save(compounders);
                organization.get().getCompounders().add(compounders);
                organizationRepository.save(organization.get());
            }
        }
        return compounders;
    }

    public List<Compounders> getAllCompoundersOfChamber() {
        return compoundersRepository.findAll();
    }

    public Compounders updateCompounderOfChamber(CompoundersChamberDTO compoundersChamberDTO, Long id) {
        Optional<Compounders> compounders = compoundersRepository.findById(id);
        if (compounders.isPresent()) {
            Compounders compounders1 = new Compounders();
            compounders1.setName(compoundersChamberDTO.getName());
            compounders1.setDepartment(compoundersChamberDTO.getDepartment());
            return compoundersRepository.save(compounders1);
        }
        return new Compounders();
    }

    public Compounders getCompounderById(@PathVariable Long id) {
        Optional<Compounders> compounders = compoundersRepository.findById(id);
        if (compounders.isPresent())
            return compounders.get();
        return new Compounders();
    }
}
