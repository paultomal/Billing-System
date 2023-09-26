package com.paul.billing_system.entity;

import jakarta.persistence.*;
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

    private String brandName;

    private double price;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private DrugVendor vendor;

    @ManyToOne
    @JoinColumn(name = "generic_id")
    private Generic generic;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private DrugFormation formation;

    @ManyToOne
    @JoinColumn(name = "strength_id")
    private DrugStrength strength;

}