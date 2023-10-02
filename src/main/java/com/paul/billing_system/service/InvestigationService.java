package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.OrgInvestigationPrice;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.OrgInvestigationPriceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
            investigation.get().setUpdatedAt(new Date());

            return investigationRepository.save(investigation.get());
        }

        return null;
    }

    public List<Investigation> searchInvestigation(String name, PageRequest pageRequest) {
        return investigationRepository.searchByName(name, pageRequest);
    }

    public List<InvestigationDTO> getAllInvestigations(Long orgId, PageRequest pageRequest) {

        List<InvestigationDTO> investigations = investigationRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(InvestigationDTO::form)
                .toList();

        return investigations.stream()
                .peek(
                        investigationDTO -> {
                            OrgInvestigationPrice orgInvestigationPrice = orgInvestigationPriceRepository
                                    .findByOrganizationAndInvestigation(orgId, investigationDTO.getId());
                            if (orgInvestigationPrice != null) {
                                if (orgInvestigationPrice.getOrgInvestigationCharge() != null)
                                    investigationDTO.setOrgInvestigationCharge(orgInvestigationPrice.getOrgInvestigationCharge());
                            }
                        }).toList();
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
                .peek(drugDTO -> {
                    OrgInvestigationPrice orgInvestigationPrice = orgInvestigationPriceRepository.findByOrganizationAndInvestigation(orgId, drugDTO.getId());
                    if (orgInvestigationPrice != null) {
                        if (orgInvestigationPrice.getOrgInvestigationCharge() != null) {
                            drugDTO.setOrgInvestigationCharge(orgInvestigationPrice.getOrgInvestigationCharge());
                        }
                    }
                })
                .toList();
    }
}
