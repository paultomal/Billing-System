package com.paul.billing_system.service;

import com.paul.billing_system.dto.SpecialistDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialistServices {
    private final SpecialistRepository specialistRepository;

    private final OrganizationRepository organizationRepository;

    public SpecialistServices(SpecialistRepository specialistRepository, OrganizationRepository organizationRepository) {
        this.specialistRepository = specialistRepository;
        this.organizationRepository = organizationRepository;
    }

    public Page<Specialist> getAllSpecialist(int offset, int pageSize) {
        return specialistRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public Specialist getMedicalSpecialist(Long id) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        if (specialist.isPresent())
            return specialist.get();
        return new Specialist();
    }

    public List<Specialist> searchSpecialist(String name) {
       return specialistRepository.findBySearch(name);
    }
}
