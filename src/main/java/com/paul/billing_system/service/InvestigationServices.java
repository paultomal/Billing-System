package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationServices {
    private final InvestigationRepository investigationRepository;
    private final SpecialistRepository specialistRepository;

    public InvestigationServices(InvestigationRepository investigationRepository, SpecialistRepository specialistRepository) {
        this.investigationRepository = investigationRepository;
        this.specialistRepository = specialistRepository;
    }

    public Investigation save(Long id, InvestigationDTO investigationDTO) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        Investigation investigation = new Investigation();
        if (specialist.isPresent()) {
            investigation.setServiceName(investigationDTO.getServiceName());
            investigation.setServiceCharge(investigationDTO.getServiceCharge());
            investigationRepository.save(investigation);
            specialist.get().getInvestigations().add(investigation);
            specialistRepository.save(specialist.get());
        }
        return investigation;
    }

    public List<Investigation> getAllServices() {
        return investigationRepository.findAll();
    }

    public Investigation getServiceById(Long id) {
        Optional<Investigation> servicesInfo = investigationRepository.findById(id);
        if (servicesInfo.isPresent()) {
            return servicesInfo.get();
        }
        return new Investigation();
    }

    public Investigation updateService(InvestigationDTO investigationDTO, Long id) {
        Optional<Investigation> servicesInfo = investigationRepository.findById(id);
        if (servicesInfo.isPresent()) {
            Investigation investigation1 = new Investigation();
            investigation1.setServiceName(investigationDTO.getServiceName());
            investigation1.setServiceCharge(investigationDTO.getServiceCharge());
            return investigationRepository.save(investigation1);
        }
        return new Investigation();
    }
}
