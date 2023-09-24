package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.entity.Specialist;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientsDTO {

    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    private String contact;

    private LocalDate since;

    private Long orgId;

    private Long spId;


    public static PatientsDTO form(Patients patients){
        PatientsDTO patientsDTO = new PatientsDTO();
        patientsDTO.setId(patients.getId());
        patientsDTO.setName(patients.getName());
        patientsDTO.setContact(patients.getContact());
        patientsDTO.setSince(patients.getSince());

        patientsDTO.setOrgId(patients.getOrganization().getId());
        patientsDTO.setSpId(patients.getSpecialist().getId());
        return patientsDTO;
    }
}
