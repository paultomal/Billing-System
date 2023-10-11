package com.paul.billing_system.service;

import com.paul.billing_system.dto.OrgInvestigationDTO;
import com.paul.billing_system.entity.OrgInvestigationPrice;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.OrgInvestigationPriceRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrgBasedInvestigationService {
    private final OrgInvestigationPriceRepository orgInvestigationPriceRepository;

    private final InvestigationRepository investigationRepository;
    private final OrganizationRepository organizationRepository;

    public OrgBasedInvestigationService(OrgInvestigationPriceRepository orgInvestigationPriceRepository,
                                        InvestigationRepository investigationRepository,
                                        OrganizationRepository organizationRepository) {
        this.orgInvestigationPriceRepository = orgInvestigationPriceRepository;
        this.investigationRepository = investigationRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public OrgInvestigationDTO updateInvestigationOrgPrice(OrgInvestigationDTO orgInvestigationDTO) {
        OrgInvestigationPrice orgInvestigationPrice = orgInvestigationPriceRepository.findByOrganizationAndInvestigation(orgInvestigationDTO.getOrgId(), orgInvestigationDTO.getInvestigationId());
        if (orgInvestigationPrice == null) {
            orgInvestigationPrice = new OrgInvestigationPrice();
        }
        orgInvestigationPrice.setOrganization(organizationRepository.findById(orgInvestigationDTO.getOrgId()).orElseThrow());
        orgInvestigationPrice.setInvestigation(investigationRepository.findById(orgInvestigationDTO.getInvestigationId()).orElseThrow());
        orgInvestigationPrice.setServiceCharge(orgInvestigationDTO.getServiceCharge());
        return OrgInvestigationDTO.form(orgInvestigationPriceRepository.save(orgInvestigationPrice));
    }


}