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

    @NotEmpty(message = "Name should not be empty")
    private String name;
    private String designation;

    public static CompoundersDTO form(Compounders compounders){
        CompoundersDTO compoundersDTO = new CompoundersDTO();
        compoundersDTO.setName(compounders.getName());
        compoundersDTO.setDesignation(compounders.getDesignation());
        return compoundersDTO;
    }
}
