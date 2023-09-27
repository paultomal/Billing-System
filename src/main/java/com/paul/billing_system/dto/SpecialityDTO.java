package com.paul.billing_system.dto;

import com.paul.billing_system.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityDTO {
    private Long id;

    private String medSpecName;

    private String iconUrl;

    public static SpecialityDTO form(Speciality speciality){
        SpecialityDTO specialityDTO = new SpecialityDTO();
        specialityDTO.setId(speciality.getId());
        specialityDTO.setMedSpecName(speciality.getMedSpecName());
        specialityDTO.setIconUrl(speciality.getIconUrl());
        return specialityDTO;
    }
}
