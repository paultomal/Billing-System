package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Speciality;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.SpecialityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvestigationServices {
    private final InvestigationRepository investigationRepository;


    public InvestigationServices(InvestigationRepository investigationRepository) {
        this.investigationRepository = investigationRepository;

    }

    @Transactional
    public Investigation saveInvestigation(InvestigationDTO investigationDTO) {
        Investigation investigation = new Investigation();
            investigation.setServiceName(investigationDTO.getServiceName());
            investigation.setServiceCharge(investigationDTO.getServiceCharge());
            investigationRepository.save(investigation);
        return investigation;
    }

    public List<Investigation> getAllServices( PageRequest pageRequest) {
                return investigationRepository.findAll(pageRequest).getContent();
    }

    public Investigation getServiceById(Long id) {
        Optional<Investigation> servicesInfo = investigationRepository.findById(id);
        return servicesInfo.orElse(null);
    }

    public Investigation updateService(InvestigationDTO investigationDTO, Long id) {

        Optional<Investigation> investigation = investigationRepository.findById(id);

        if (investigation.isPresent()) {
            investigation.get().setServiceName(investigationDTO.getServiceName());
            investigation.get().setServiceCharge(investigationDTO.getServiceCharge());
            investigation.get().setUpdatedAt(new Date());

            return investigationRepository.save(investigation.get());
        }

        return null;
    }

    public List<Investigation> searchInvestigation(String name, PageRequest pageRequest) {
        return investigationRepository.searchByName(name, pageRequest);
    }
}
