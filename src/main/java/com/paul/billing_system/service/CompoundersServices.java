package com.paul.billing_system.service;

import com.paul.billing_system.dto.CompoundersDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.repository.CompoundersRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

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
    public Compounders saveCompounders(Long id, CompoundersDTO compoundersDTO) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Compounders compounders = new Compounders();
        if (organization.isPresent()) {
            compounders.setName(compoundersDTO.getName());
            compounders.setDesignation(compoundersDTO.getDesignation());
            compounders.setDepartment(compoundersDTO.getDepartment());
            compoundersRepository.save(compounders);
            organization.get().getCompounders().add(compounders);
            organizationRepository.save(organization.get());
        }

        return compounders;
    }

    public Compounders updateCompounder(CompoundersDTO compoundersDTO, Long id) {
        Optional<Compounders> compounders = compoundersRepository.findById(id);
        if (compounders.isPresent()) {
            Compounders compounders1 = new Compounders();
            compounders1.setName(compoundersDTO.getName());
            compounders1.setDesignation(compoundersDTO.getDesignation());
            compounders1.setDepartment(compoundersDTO.getDepartment());
            return compoundersRepository.save(compounders1);
        }
        return new Compounders();
    }

    public Compounders getCompounderById(Long id) {
        Optional<Compounders> compounders = compoundersRepository.findById(id);
        if (compounders.isPresent())
            return compounders.get();
        return new Compounders();
    }


/*    public List<Compounders> getAllCompounders(Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent()) {
            return compoundersRepository.findAll();
        }
        return (List<Compounders>) new Compounders();
    }*/


/*    //Chamber Compounder Services
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

    public List<Compounders> getAllCompoundersOfChamber(Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent()) {
            if (organization.get().getType().equals(OrganizationTypes.CHAMBER)) {
                return compoundersRepository.findAll();
            }
        }
        return (List<Compounders>) new Compounders();
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
    }*/
}
