package com.paul.billing_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Specialist implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medSpecName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "specialitist_doctors",
            joinColumns = @JoinColumn(name = "specialist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "doctors_id")
    )
    private List<Doctors> doctors;

    private long noOfDoctor = !(doctors == null) ? doctors.size() : 0;

    private String iconUrl;

    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investigation> investigations;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "specialitist_admin",
            joinColumns = @JoinColumn(name = "specialitist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id")
    )
    private List<UserInfo> admin;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "specialitist_patients",
            joinColumns = @JoinColumn(name = "specialitist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patients_id")
    )
    private List<Patients> patients;

}
