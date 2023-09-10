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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "medicalspecialist_doctors",
            joinColumns = @JoinColumn(name = "medSpecialist_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "doctors_id")
    )
    private List<Doctors> doctors;

}
