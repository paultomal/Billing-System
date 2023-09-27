package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Drug;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDTO {
    private Long id;

    private String brandName;

    private double price;

    private String vendorName;

    private String genericName;

    private String formationName;

    private String strengthName;

    public static DrugDTO form(Drug drug) {
        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setId(drug.getId());
        drugDTO.setBrandName(drug.getBrandName());
        drugDTO.setPrice(drug.getPrice());
        drugDTO.setVendorName(drug.getVendor().getName());
        drugDTO.setFormationName(drug.getFormation().getName());
        drugDTO.setGenericName(drug.getGeneric().getName());
        drugDTO.setStrengthName(drug.getStrength().getName());

        return drugDTO;
    }

}
