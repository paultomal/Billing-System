package com.paul.billing_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DrugOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    private String patientContact;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToMany
    @JoinTable(
            name = "order_drug",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id")
    )
    @NotEmpty
    private List<Drug> drugList;

    private double total;
}
