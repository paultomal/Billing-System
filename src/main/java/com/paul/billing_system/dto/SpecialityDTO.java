package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Speciality;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityDTO {
    private Long id;

    @Column(unique = true)
    private String medSpecName;

    private String iconUrl;

    private String description;

    public static SpecialityDTO form(Speciality speciality) {
        SpecialityDTO specialityDTO = new SpecialityDTO();
        specialityDTO.setId(speciality.getId());
        specialityDTO.setMedSpecName(speciality.getMedSpecName());
        specialityDTO.setIconUrl(speciality.getIconUrl());
        specialityDTO.setDescription(speciality.getDescription());
        return specialityDTO;
    }
}
