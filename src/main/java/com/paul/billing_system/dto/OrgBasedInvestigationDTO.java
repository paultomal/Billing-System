package com.paul.billing_system.dto;

import com.paul.billing_system.entity.OrgBasedInvestigation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgBasedInvestigationDTO {
    private Long id;

    @NotNull(message = "Name should not be empty")
    private String serviceName;

    private Double orgServiceCharge;

    private Long orgId;

    public static OrgBasedInvestigationDTO form(OrgBasedInvestigation orgBasedInvestigation){
        OrgBasedInvestigationDTO orgBasedInvestigationDTO = new OrgBasedInvestigationDTO();
        orgBasedInvestigationDTO.setServiceName(orgBasedInvestigation.getServiceName());
        orgBasedInvestigationDTO.setOrgServiceCharge(orgBasedInvestigation.getOrgServiceCharge());
        orgBasedInvestigationDTO.setOrgId(orgBasedInvestigation.getOrganization().getId());
        return orgBasedInvestigationDTO;
    }
}
