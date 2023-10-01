package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.dto.OrgBasedInvestigationDTO;
import com.paul.billing_system.entity.OrgBasedInvestigation;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.OrgBasedInvestigationRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrgBasedInvestigationService {
    private final OrgBasedInvestigationRepository orgBasedInvestigationRepository;

    private final InvestigationRepository investigationRepository;
    private final OrganizationRepository organizationRepository;

    public OrgBasedInvestigationService(OrgBasedInvestigationRepository orgBasedInvestigationRepository,
                                        InvestigationRepository investigationRepository,
                                        OrganizationRepository organizationRepository) {
        this.orgBasedInvestigationRepository = orgBasedInvestigationRepository;
        this.investigationRepository = investigationRepository;
        this.organizationRepository = organizationRepository;
    }


    public OrgBasedInvestigation addOrgInvestigation(OrgBasedInvestigationDTO orgBasedInvestigationDTO) {

        OrgBasedInvestigation orgBasedInvestigation = new OrgBasedInvestigation();
        orgBasedInvestigation.setOrganization(organizationRepository.findById(orgBasedInvestigationDTO.getOrgId()).orElse(null));
        orgBasedInvestigation.setInvestigation(investigationRepository.findById(orgBasedInvestigationDTO.getInvestigationId()).orElse(null));
        orgBasedInvestigation.setOrgInvestigationCharge(orgBasedInvestigationDTO.getOrgInvestigationCharge());
        
        return orgBasedInvestigationRepository.save(orgBasedInvestigation);
    }

    public List<OrgBasedInvestigation> addMultipleOrgInvestigation(List<OrgBasedInvestigationDTO> investigationDTOList) {
        List<OrgBasedInvestigation> orgBasedInvestigationList = new ArrayList<>();

        investigationDTOList.forEach(orgBasedInvestigationDTO -> {
            OrgBasedInvestigation orgBasedInvestigation = new OrgBasedInvestigation();

            orgBasedInvestigation.setOrganization(
                    organizationRepository.findById(orgBasedInvestigationDTO.getOrgId()).orElse(null)
            );
            orgBasedInvestigation.setInvestigation(
                    investigationRepository.findById(orgBasedInvestigationDTO.getInvestigationId()).orElse(null)
            );

            orgBasedInvestigation.setOrgInvestigationCharge(orgBasedInvestigationDTO.getOrgInvestigationCharge());

            orgBasedInvestigationList.add(orgBasedInvestigation);
        });

        return orgBasedInvestigationRepository.saveAll(orgBasedInvestigationList);
    }


    public List<OrgBasedInvestigation> getAllOrgInvestigationById(Long orgId) {
        Optional<Organization> organization = organizationRepository.findById(orgId);
        if (organization.isPresent())
            return orgBasedInvestigationRepository.findByOrganization(orgId);
        return null;
    }
}
