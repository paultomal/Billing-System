package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Investigation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestigationDTO {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Column(unique = true)
    private String serviceName;

    private Double serviceCharge;

    public static InvestigationDTO form(Investigation investigation) {
        InvestigationDTO investigationDTO = new InvestigationDTO();
        investigationDTO.setId(investigation.getId());
        investigationDTO.setServiceName(investigation.getServiceName());
        investigationDTO.setServiceCharge(investigation.getServiceCharge());
        return investigationDTO;
    }
}
