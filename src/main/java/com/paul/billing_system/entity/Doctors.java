package com.paul.billing_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorName;

    private String doctorDegree;

    @ManyToOne()
    @JoinTable(
            name = "doctors_medicalspecialist",
            joinColumns = @JoinColumn(name = "doctors_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medSpecialist_id")
    )
    private MedSpecialist medSpecialist;
}
