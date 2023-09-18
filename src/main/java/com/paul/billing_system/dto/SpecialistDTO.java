package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.entity.UserInfo;
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

    private String noOfDoctor;

    private List<DoctorDTO> doctors;

    private List<InvestigationDTO> investigations;

    private List<UserInfoDTO> admin;

    public static SpecialistDTO form(Specialist specialist){
        SpecialistDTO specialistDTO = new SpecialistDTO();
        specialistDTO.setId(specialist.getId());
        specialistDTO.setMedSpecName(specialist.getMedSpecName());
        specialistDTO.setNoOfDoctor(specialist.getNoOfDoctor());
        specialistDTO.setDoctors(specialist.getDoctors() != null ? specialist.getDoctors().stream().map(DoctorDTO::form).toList() : null);
        specialistDTO.setInvestigations(specialist.getInvestigations() != null ? specialist.getInvestigations().stream().map(InvestigationDTO::form).toList() : null);
        specialistDTO.setAdmin(specialist.getAdmin() != null ? specialist.getAdmin().stream().map(UserInfoDTO::form).toList() : null);
        return specialistDTO;
    }
}
