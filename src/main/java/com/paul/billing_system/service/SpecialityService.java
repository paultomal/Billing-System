package com.paul.billing_system.service;

import com.paul.billing_system.dto.SpecialityDTO;
import com.paul.billing_system.entity.Speciality;
import com.paul.billing_system.repository.SpecialityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;


    public SpecialityService(SpecialityRepository specialityRepository) {
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

    public Speciality addSpeciality(SpecialityDTO specialityDTO) {
        Speciality speciality = new Speciality();
        speciality.setMedSpecName(specialityDTO.getMedSpecName());
        speciality.setIconUrl(specialityDTO.getIconUrl());
        speciality.setDescription(specialityDTO.getDescription());
        speciality.setCreatedAt(new Date());
        return specialityRepository.save(speciality);
    }

    public Speciality updateSpeciality(Long sId, SpecialityDTO specialityDTO) {
        Optional<Speciality> speciality = specialityRepository.findById(sId);

        if (speciality.isPresent()) {
            speciality.get().setMedSpecName(specialityDTO.getMedSpecName());
            speciality.get().setIconUrl(specialityDTO.getIconUrl());
            speciality.get().setDescription(specialityDTO.getDescription());
            speciality.get().setUpdatedAt(new Date());
            return specialityRepository.save(speciality.get());
        }
        return null;
    }

    public Long countSpeciality() {
        return (long) specialityRepository.findAll().size();
    }
}
