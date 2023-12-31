package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.OrgInvestigationPrice;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.OrgInvestigationPriceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationService {
    private final InvestigationRepository investigationRepository;
    private final OrgInvestigationPriceRepository orgInvestigationPriceRepository;


    public InvestigationService(InvestigationRepository investigationRepository, OrgInvestigationPriceRepository orgInvestigationPriceRepository) {
        this.investigationRepository = investigationRepository;

        this.orgInvestigationPriceRepository = orgInvestigationPriceRepository;
    }

    @Transactional
    public Investigation saveInvestigation(InvestigationDTO investigationDTO) {
        Investigation investigation = new Investigation();
        investigation.setServiceName(investigationDTO.getServiceName());
        investigation.setServiceCharge(investigationDTO.getServiceCharge());
        investigationRepository.save(investigation);
        return investigation;
    }

    public List<Investigation> getAllServices(PageRequest pageRequest) {
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
            //investigation.get().setUpdatedAt(new Date());

            return investigationRepository.save(investigation.get());
        }

        return null;
    }

    public List<Investigation> searchInvestigation(String name, PageRequest pageRequest) {
        return investigationRepository.searchByName(name, pageRequest);
    }

    public List<InvestigationDTO> searchInvestigationBYOrg(Long orgId, String name, PageRequest pageRequest) {
        List<InvestigationDTO> searchInvestigation = investigationRepository.searchByName(name, pageRequest)
                .stream()
                .map(InvestigationDTO::form)
                .toList();
        return getInvestigationWithCharge(orgId, searchInvestigation)
                .stream()
                .toList();

    }


    public List<InvestigationDTO> getAllInvestigationByOrg(Long orgId, PageRequest pageRequest) {
        List<InvestigationDTO> investigation = investigationRepository.findAll(pageRequest).getContent()
                .stream()
                .map(InvestigationDTO::form)
                .toList();
        return getInvestigationWithCharge(orgId, investigation);
    }


    private List<InvestigationDTO> getInvestigationWithCharge(Long orgId, List<InvestigationDTO> investigation) {
        return investigation.stream()
                .peek(investigationDTO -> {
                    OrgInvestigationPrice orgInvestigationPrice = orgInvestigationPriceRepository.findByOrganizationAndInvestigation(orgId, investigationDTO.getId());
                    if (orgInvestigationPrice != null) {
                        if (orgInvestigationPrice.getServiceCharge() != null) {
                            investigationDTO.setServiceCharge(orgInvestigationPrice.getServiceCharge());
                        }
                    }
                })
                .toList();
    }

    public InvestigationDTO getOrgBasedInvestigation(Long id, Long orgId) {
        Optional<Investigation> investigation = investigationRepository.findById(id);

        OrgInvestigationPrice orgInvestigationPrice = null;
        if (investigation.isPresent()) {
            orgInvestigationPrice = orgInvestigationPriceRepository.findByOrganizationAndInvestigation(orgId, investigation.get().getId());
        }
        InvestigationDTO investigationDTO = investigation.map(InvestigationDTO::form).orElse(null);
        assert investigationDTO != null;
        if (orgInvestigationPrice != null) {
            if (orgInvestigationPrice.getServiceCharge() != null)
                investigationDTO.setServiceCharge(orgInvestigationPrice.getServiceCharge());
        }
        return investigationDTO;
    }

    public Long countInvestigation() {
        return (long) investigationRepository.findAll().size();
    }

    public Optional<Investigation> getInvestigationByServiceName(String serviceName) {
        return investigationRepository.findByServiceName(serviceName);
    }
}



