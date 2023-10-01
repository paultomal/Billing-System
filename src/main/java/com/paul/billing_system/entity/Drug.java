package com.paul.billing_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Drug extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String brandName;

    @NotNull
    private Double price;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @NotNull
    private DrugVendor vendor;

    @ManyToOne
    @JoinColumn(name = "generic_id")
    @NotNull
    private Generic generic;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    @NotNull
    private DrugFormation formation;

    @ManyToOne
    @JoinColumn(name = "strength_id")
    @NotNull
    private DrugStrength strength;

}