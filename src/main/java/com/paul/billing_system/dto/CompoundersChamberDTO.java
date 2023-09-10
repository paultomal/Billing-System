package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Compounders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompoundersChamberDTO {

    private Long id;

    private String name;

    private String department;

    public static CompoundersChamberDTO form(Compounders compounders){
        CompoundersChamberDTO compoundersChamberDTO = new CompoundersChamberDTO();
        compoundersChamberDTO.setId(compounders.getId());
        compoundersChamberDTO.setName(compounders.getName());
        compoundersChamberDTO.setDepartment(compounders.getDepartment());
        return compoundersChamberDTO;
    }
}
