package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Compounders;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompoundersHospitalDTO {

    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;
    private String designation;

    public static CompoundersHospitalDTO form(Compounders compounders){
        CompoundersHospitalDTO compoundersHospitalDTO = new CompoundersHospitalDTO();
        compoundersHospitalDTO.setId(compounders.getId());
        compoundersHospitalDTO.setName(compounders.getName());
        compoundersHospitalDTO.setDesignation(compounders.getDesignation());
        return compoundersHospitalDTO;
    }
}
