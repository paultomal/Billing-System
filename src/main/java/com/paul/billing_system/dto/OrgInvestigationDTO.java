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

    private String serviceName;

    private Double serviceCharge;

    public static OrgInvestigationDTO form(OrgInvestigationPrice orgInvestigationPrice) {
        OrgInvestigationDTO orgInvestigationDTO = new OrgInvestigationDTO();
        orgInvestigationDTO.setOrgId(orgInvestigationPrice.getOrganization().getId());
        orgInvestigationDTO.setInvestigationId(orgInvestigationPrice.getInvestigation().getId());
        orgInvestigationDTO.setServiceName(orgInvestigationPrice.getInvestigation().getServiceName());
        orgInvestigationDTO.setServiceCharge(orgInvestigationPrice.getServiceCharge());
        return orgInvestigationDTO;
    }
}
