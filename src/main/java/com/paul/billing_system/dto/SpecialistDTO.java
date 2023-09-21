package com.paul.billing_system.dto;

import com.paul.billing_system.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDTO {
    private Long id;

    private String medSpecName;

    private String iconUrl;

    //private long noOfDoctor;

    /*
        private List<DoctorDTO> doctors;

        private List<InvestigationDTO> investigations;

        private List<UserInfoDTO> admin;

        private List<PatientsDTO> patients;
    */
    public static SpecialistDTO form(Specialist specialist){
        SpecialistDTO specialistDTO = new SpecialistDTO();
        specialistDTO.setId(specialist.getId());
        specialistDTO.setMedSpecName(specialist.getMedSpecName());
        specialistDTO.setIconUrl(specialist.getIconUrl());
/*        specialistDTO.setNoOfDoctor(specialist.getNoOfDoctor());
        specialistDTO.setDoctors(specialist.getDoctors() != null ? specialist.getDoctors().stream().map(DoctorDTO::form).toList() : null);
        specialistDTO.setInvestigations(specialist.getInvestigations() != null ? specialist.getInvestigations().stream().map(InvestigationDTO::form).toList() : null);
        specialistDTO.setAdmin(specialist.getAdmin() != null ? specialist.getAdmin().stream().map(UserInfoDTO::form).toList() : null);
        specialistDTO.setPatients(specialist.getPatients() != null ? specialist.getPatients().stream().map(PatientsDTO::form).toList() : null);*/
        return specialistDTO;
    }
}
