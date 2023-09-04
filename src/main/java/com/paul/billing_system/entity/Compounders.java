package com.paul.billing_system.entity;

import com.paul.billing_system.dto.CompoundersDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String name;
    private String designation;

    public static Compounders form(CompoundersDTO compoundersDTO) {
    Compounders compounders = new Compounders();
    compounders.setName(compoundersDTO.getName());
    compounders.setDesignation(compoundersDTO.getDesignation());
    return compounders;
    }
}
