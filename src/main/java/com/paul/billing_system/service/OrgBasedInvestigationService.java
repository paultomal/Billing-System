package com.paul.billing_system.service;

import com.paul.billing_system.dto.OrgInvestigationDTO;
import com.paul.billing_system.entity.OrgInvestigationPrice;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.OrgInvestigationPriceRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public List<OrgInvestigationPrice> addMultipleOrgInvestigation(List<OrgInvestigationDTO> investigationDTOList) {
        List<OrgInvestigationPrice> orgInvestigationPriceList = new ArrayList<>();

        investigationDTOList.forEach(orgBasedInvestigationDTO -> {
            OrgInvestigationPrice orgInvestigationPrice = new OrgInvestigationPrice();

            orgInvestigationPrice.setOrganization(
                    organizationRepository.findById(orgBasedInvestigationDTO.getOrgId()).orElse(null)
            );
            orgInvestigationPrice.setInvestigation(
                    investigationRepository.findById(orgBasedInvestigationDTO.getInvestigationId()).orElse(null)
            );

            orgInvestigationPrice.setServiceCharge(orgBasedInvestigationDTO.getServiceCharge());

            orgInvestigationPriceList.add(orgInvestigationPrice);
        });

        return orgInvestigationPriceRepository.saveAll(orgInvestigationPriceList);
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




/*    public OrgInvestigationPrice addOrgInvestigation(OrgInvestigationDTO orgInvestigationDTO) {

        OrgInvestigationPrice orgInvestigationPrice = new OrgInvestigationPrice();
        orgInvestigationPrice.setOrganization(organizationRepository.findById(orgInvestigationDTO.getOrgId()).orElse(null));
        orgInvestigationPrice.setInvestigation(investigationRepository.findById(orgInvestigationDTO.getInvestigationId()).orElse(null));
        orgInvestigationPrice.setOrgInvestigationCharge(orgInvestigationDTO.getOrgInvestigationCharge());

        return orgInvestigationPriceRepository.save(orgInvestigationPrice);
    }*/
