package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Drug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDTO {
    private Long id;

    private String brandName;

    public static DrugDTO form(Drug drug) {
        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setId(drug.getId());
        drugDTO.setBrandName(drug.getBrandName());

        return drugDTO;
    }
}
