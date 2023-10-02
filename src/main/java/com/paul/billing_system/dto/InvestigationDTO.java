package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Investigation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestigationDTO {
    private Long id;

    private String serviceName;

    private Double serviceCharge;

    private Double orgInvestigationCharge;

    public static InvestigationDTO form(Investigation investigation) {
        InvestigationDTO investigationDTO = new InvestigationDTO();
        investigationDTO.setId(investigation.getId());
        investigationDTO.setServiceName(investigation.getServiceName());
        investigationDTO.setServiceCharge(investigation.getServiceCharge());
        return investigationDTO;
    }
}
