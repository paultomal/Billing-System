package com.paul.billing_system.dto;

import com.paul.billing_system.entity.DrugOrder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugOrderDTO {
    private Long id;

    @NotNull
    private String patientName;

    @NotNull
    private String patientContact;

    @NotNull
    private Long orgId;

    @NotNull
    private List<DrugDTO> drugList;

    @NotNull
    private double total;

    public static DrugOrderDTO form(DrugOrder drugOrder) {
        DrugOrderDTO drugOrderDTO = new DrugOrderDTO();
        drugOrderDTO.setId(drugOrder.getId());
        drugOrderDTO.setPatientName(drugOrder.getPatientName());
        drugOrderDTO.setPatientContact(drugOrder.getPatientContact());
        drugOrderDTO.setOrgId(drugOrder.getOrganization().getId());
        drugOrderDTO.setDrugList(drugOrder.getDrugList().stream().map(DrugDTO::form).toList());
        drugOrderDTO.setTotal(drugOrder.getTotal());

        return drugOrderDTO;
    }
}
