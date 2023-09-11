package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Compounders;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompoundersDTO {

    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;
    private String designation;
    private String department;
    public static CompoundersDTO form(Compounders compounders){
        CompoundersDTO compoundersDTO = new CompoundersDTO();
        compoundersDTO.setId(compounders.getId());
        compoundersDTO.setName(compounders.getName());
        compoundersDTO.setDesignation(compounders.getDesignation());
        compoundersDTO.setDepartment(compounders.getDepartment());
        return compoundersDTO;
    }
}
