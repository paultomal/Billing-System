package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.entity.Specialist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDTO {
    private Long id;

    private String specialityName;

    private List<DoctorDTO> doctors;



    public static SpecialistDTO form(Specialist specialist){
        SpecialistDTO specialistDTO = new SpecialistDTO();
        specialistDTO.setId(specialist.getId());
        specialistDTO.setSpecialityName(specialist.getSpecialityName());
        specialistDTO.setDoctors(specialist.getDoctors() != null ? specialist.getDoctors().stream().map(DoctorDTO::form).toList() : null);
        return specialistDTO;
    }
}
