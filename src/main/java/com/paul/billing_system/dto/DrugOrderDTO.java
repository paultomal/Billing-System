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

    private Long patientId;

    private String patientName;

    private String patientContact;

    @NotNull
    private Long orgId;

    @NotNull
    private List<DrugDTO> drugList;

    @NotNull
    private Double total;

    public static DrugOrderDTO form(DrugOrder drugOrder) {
        DrugOrderDTO drugOrderDTO = new DrugOrderDTO();
        drugOrderDTO.setId(drugOrder.getId());
        drugOrderDTO.setPatientId(drugOrder.getPatient().getId());
        drugOrderDTO.setPatientName(drugOrder.getPatient().getName());
        drugOrderDTO.setPatientContact(drugOrder.getPatient().getContact());
        drugOrderDTO.setOrgId(drugOrder.getOrganization().getId());
        drugOrderDTO.setDrugList(drugOrder.getDrugList().stream().map(DrugDTO::form).toList());
        drugOrderDTO.setTotal(drugOrder.getTotal());

        return drugOrderDTO;
    }
}
