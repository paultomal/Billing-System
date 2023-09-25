package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvestigationServices {
    private final InvestigationRepository investigationRepository;
    private final OrganizationRepository organizationRepository;
    private final SpecialistRepository specialistRepository;

    public InvestigationServices(InvestigationRepository investigationRepository, OrganizationRepository organizationRepository, SpecialistRepository specialistRepository) {
        this.investigationRepository = investigationRepository;
        this.organizationRepository = organizationRepository;
        this.specialistRepository = specialistRepository;
    }

    @Transactional
    public Investigation saveInvestigation(Long id, InvestigationDTO investigationDTO) {
        Optional<Organization> organization1 = organizationRepository.findById(id);
        Investigation investigation = new Investigation();
        if (organization1.isPresent()) {
            investigation.setServiceName(investigationDTO.getServiceName());
            investigation.setServiceCharge(investigationDTO.getServiceCharge());

            Organization organization = organizationRepository.findById(investigationDTO.getOrgId()).orElseThrow(RuntimeException::new);
            investigation.setOrganization(organization);

            Specialist specialist = specialistRepository.findById(investigationDTO.getSpId()).orElseThrow(RuntimeException::new);
            investigation.setSpecialist(specialist);

            investigationRepository.save(investigation);

        }
        return investigation;
    }

    public List<Investigation> getAllServices(Long id, Long spId, PageRequest pageRequest) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Optional<Specialist> specialist = specialistRepository.findById(spId);
        if (organization.isPresent())
            if (specialist.isPresent())
                return investigationRepository.findByOrganizationAndSpecialist(id, spId, pageRequest);
        return null;
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
            investigation.get().setLastUpdateTime(new Date());

            return investigationRepository.save(investigation.get());
        }

        return null;
    }

    public List<Investigation> searchInvestigation(String name, PageRequest pageRequest) {
        return investigationRepository.searchByName(name, pageRequest);
    }
}
