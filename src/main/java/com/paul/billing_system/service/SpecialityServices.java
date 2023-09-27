package com.paul.billing_system.service;

import com.paul.billing_system.entity.Speciality;
import com.paul.billing_system.repository.SpecialityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityServices {
    private final SpecialityRepository specialityRepository;


    public SpecialityServices(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public Page<Speciality> getAllSpecialist(PageRequest pageRequest) {
        return specialityRepository.findAll(pageRequest);
    }

    public Speciality getMedicalSpecialist(Long id) {
        Optional<Speciality> specialist = specialityRepository.findById(id);
        return specialist.orElseGet(Speciality::new);
    }

    public List<Speciality> searchSpecialist(String name, PageRequest pageRequest) {
        return specialityRepository.findBySearch(name, pageRequest);
    }
}
