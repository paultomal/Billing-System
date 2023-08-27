package com.paul.billing_system.service;

import com.paul.billing_system.dto.OrganizationDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServices {
    private final OrganizationRepository organizationRepository;

    public OrganizationServices(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization save(OrganizationDTO organizationDTO) {
        Organization organization =  new Organization();
        organization.setName(organizationDTO.getName());
        organization.setAddress(organizationDTO.getAddress());
        organization.setContact(organizationDTO.getContact());
        OrganizationTypes organizationType = OrganizationTypes.getOrganizationTypeByLabel(organizationDTO.getType());
        organization.setType(organizationType);
        organization.setEmail(organizationDTO.getEmail());
        organization.setWebsite(organizationDTO.getWebsite());
        return organizationRepository.save(organization);
    }

    public List<Organization> getAllOrganization() {
        return organizationRepository.findAll();
    }

    public Organization getOrganizationByid(Long id) {
        Optional<Organization> organization =organizationRepository.findById(id);
        if (organization.isPresent()){
            return organization.get();
        }
        return new Organization();
    }

    public Organization updateOrganizationProfile(OrganizationDTO organizationDTO, Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent()){
            Organization organization1 = organization.get();
            organization1.setName(organizationDTO.getName());
            organization1.setAddress(organizationDTO.getAddress());
            organization1.setContact(organizationDTO.getContact());
            organization1.setEmail(organizationDTO.getEmail());
            organization1.setWebsite(organizationDTO.getWebsite());
            return organizationRepository.save(organization1);
        }
        return new Organization();
    }
}