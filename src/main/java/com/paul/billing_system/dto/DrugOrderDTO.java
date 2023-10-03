package com.paul.billing_system.dto;

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
}
