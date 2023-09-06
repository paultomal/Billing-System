package com.paul.billing_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MedSpecialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medSpecName;

    private Long noOfDoctors;

    @OneToOne
    private Compounders compounders;

    @OneToMany(orphanRemoval = true,mappedBy = "medSpecialist")
    private List<Doctors> doctors;

}
