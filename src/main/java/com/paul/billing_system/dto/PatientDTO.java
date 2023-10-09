package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Patient;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    private String contact;

    private LocalDate since;

    private Long orgId;

    public static PatientDTO form(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setContact(patient.getContact());
        patientDTO.setSince(patient.getSince());
        patientDTO.setOrgId(patient.getOrganization().getId());

        return patientDTO;
    }
}
