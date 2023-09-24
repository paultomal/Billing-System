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

    public static SpecialistDTO form(Specialist specialist){
        SpecialistDTO specialistDTO = new SpecialistDTO();
        specialistDTO.setId(specialist.getId());
        specialistDTO.setMedSpecName(specialist.getMedSpecName());
        specialistDTO.setIconUrl(specialist.getIconUrl());
        return specialistDTO;
    }
}
