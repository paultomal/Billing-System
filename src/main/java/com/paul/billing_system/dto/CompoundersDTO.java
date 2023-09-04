package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Compounders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompoundersDTO {
    private String name;
    private String designation;

    public static CompoundersDTO form(Compounders compounders){
        CompoundersDTO staffsDTO = new CompoundersDTO();
        staffsDTO.setName(compounders.getName());
        staffsDTO.setDesignation(compounders.getDesignation());
        return staffsDTO;
    }
}
