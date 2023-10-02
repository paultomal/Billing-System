package com.paul.billing_system.dto;

import com.paul.billing_system.entity.OrgInvestigationPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgInvestigationDTO {
    private Long id;

    private Long orgId;

    private Long investigationId;

    private Double orgInvestigationCharge;

    public static OrgInvestigationDTO form(OrgInvestigationPrice orgInvestigationPrice) {
        OrgInvestigationDTO orgInvestigationDTO = new OrgInvestigationDTO();
        orgInvestigationDTO.setOrgId(orgInvestigationPrice.getOrganization().getId());
        orgInvestigationDTO.setInvestigationId(orgInvestigationPrice.getInvestigation().getId());
        orgInvestigationDTO.setOrgInvestigationCharge(orgInvestigationPrice.getOrgInvestigationCharge());
        return orgInvestigationDTO;
    }
}
