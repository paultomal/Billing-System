package com.paul.billing_system.service;

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


    public SpecialistServices(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
    }

    public Page<Specialist> getAllSpecialist(PageRequest pageRequest) {
        return specialistRepository.findAll(pageRequest);
    }

    public Specialist getMedicalSpecialist(Long id) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        return specialist.orElseGet(Specialist::new);
    }

    public List<Specialist> searchSpecialist(String name, PageRequest pageRequest) {
        return specialistRepository.findBySearch(name, pageRequest);
    }
}
