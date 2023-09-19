package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvestigationServices {
    private final InvestigationRepository investigationRepository;
    private final SpecialistRepository specialistRepository;

    public InvestigationServices(InvestigationRepository investigationRepository, SpecialistRepository specialistRepository) {
        this.investigationRepository = investigationRepository;
        this.specialistRepository = specialistRepository;
    }

    public Investigation saveInvestigation(Long id, InvestigationDTO investigationDTO) {
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

    public Page<Investigation> getAllServices(Long id, int offset, int pageSize) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        if (specialist.isPresent())
            return investigationRepository.findAll(PageRequest.of(offset,pageSize));
        return null;
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
