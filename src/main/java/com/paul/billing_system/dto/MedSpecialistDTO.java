package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.entity.MedSpecialist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedSpecialistDTO {
    private Long id;

    private String medSpecName;

    private Long noOfDoctors;



    public static MedSpecialistDTO form(MedSpecialist medSpecialist){
        MedSpecialistDTO medSpecialistDTO = new MedSpecialistDTO();
        medSpecialistDTO.setId(medSpecialist.getId());
        medSpecialistDTO.setMedSpecName(medSpecialist.getMedSpecName());
        medSpecialistDTO.setNoOfDoctors(medSpecialist.getNoOfDoctors());
        return medSpecialistDTO;
    }
}
