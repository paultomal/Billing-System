package com.paul.billing_system.service;

import com.paul.billing_system.dto.OrganizationDTO;
import com.paul.billing_system.entity.*;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServices {
    private final OrganizationRepository organizationRepository;
    private final SpecialistRepository specialistRepository;

    public OrganizationServices(OrganizationRepository organizationRepository, SpecialistRepository specialistRepository) {
        this.organizationRepository = organizationRepository;
        this.specialistRepository = specialistRepository;
    }

    public Organization save(OrganizationDTO organizationDTO) {

        Organization organization = new Organization();
        organization.setName(organizationDTO.getName());
        organization.setAddress(organizationDTO.getAddress());
        organization.setContact(organizationDTO.getContact());
        OrganizationTypes organizationType = OrganizationTypes.getOrganizationTypeByLabel(organizationDTO.getType());
        organization.setType(organizationType);
        organization.setEmail(organizationDTO.getEmail());
        organization.setEmergencyContact(organizationDTO.getEmergencyContact());
        organization.setOperatingHour(organizationDTO.getOperatingHour());

        List<Specialist> specialists = specialistRepository.findAll();

/*        List<Specialist> specialistList = organization.getSpecialists();

        List<String> list = specialistList.stream().map(Specialist::getMedSpecName).toList();*/
        organization.setSpecialists(specialists);
        organization =organizationRepository.save(organization);

        return organization;

    }


    public List<Organization> getAllOrganization(OrganizationTypes type, PageRequest pageRequest) {
        return organizationRepository.findByType(type, pageRequest);
    }

    public Organization getOrganizationByid(Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent()) {
            return organization.get();
        }
        return new Organization();
    }

    public Organization updateOrganizationProfile(OrganizationDTO organizationDTO, Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent()) {
            Organization organization1 = organization.get();
            organization1.setName(organizationDTO.getName());
            organization1.setAddress(organizationDTO.getAddress());
            organization1.setContact(organizationDTO.getContact());
            organization1.setEmail(organizationDTO.getEmail());
            organization1.setEmergencyContact(organizationDTO.getEmergencyContact());
            organization1.setOperatingHour(organizationDTO.getOperatingHour());
            return organizationRepository.save(organization1);
        }
        return new Organization();
    }

/*    public Map<Specialist, List<Doctors>> getSpecialistDoctorsMap(Long orgId, String specialityName) {
        Optional<Organization> organization = organizationRepository.findById(orgId);

    }*/
}
