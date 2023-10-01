package com.paul.billing_system.dto;

import com.paul.billing_system.entity.OrgBasedInvestigation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgBasedInvestigationDTO {
    private Long id;

    private Long orgId;

    private Long investigationId;

    private String globalServiceName;

    private Double orgInvestigationCharge;

    public static OrgBasedInvestigationDTO form(OrgBasedInvestigation orgBasedInvestigation) {
        OrgBasedInvestigationDTO orgBasedInvestigationDTO = new OrgBasedInvestigationDTO();
        orgBasedInvestigationDTO.setOrgId(orgBasedInvestigation.getOrganization().getId());
        orgBasedInvestigationDTO.setInvestigationId(orgBasedInvestigation.getInvestigation().getId());
        orgBasedInvestigationDTO.setGlobalServiceName(orgBasedInvestigation.getInvestigation().getServiceName());
        orgBasedInvestigationDTO.setOrgInvestigationCharge(orgBasedInvestigation.getOrgInvestigationCharge());
        return orgBasedInvestigationDTO;
    }
}
