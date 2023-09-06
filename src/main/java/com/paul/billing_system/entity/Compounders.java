package com.paul.billing_system.entity;

import com.paul.billing_system.dto.CompoundersChamberDTO;
import com.paul.billing_system.dto.CompoundersHospitalDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compounders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    private String designation;

    private String department;

    public static Compounders formCompunderHospital(CompoundersHospitalDTO compoundersHospitalDTO) {
    Compounders compounders = new Compounders();
    compounders.setName(compoundersHospitalDTO.getName());
    compounders.setDesignation(compoundersHospitalDTO.getDesignation());
    return compounders;
    }

    public static Compounders formCompounderChamber(CompoundersChamberDTO compoundersChamberDTO){
        Compounders compounders = new Compounders();
        compounders.setName(compoundersChamberDTO.getName());
        compounders.setDepartment(compoundersChamberDTO.getDepartment());
        return compounders;
    }
}
