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
public class Specialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medSpecName;



/*    @OneToOne
    private Compounders compounders;*/

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "specialitist_doctors",
            joinColumns = @JoinColumn(name = "specialist_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "doctors_id")
    )
    private List<Doctors> doctors;

    private long noOfDoctor = !(doctors == null) ? doctors.size() : 0;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "specialitist_investigation",
            joinColumns = @JoinColumn(name = "specialitist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "investigation_id")
    )
    private List<Investigation> investigations;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "specialitist_admin",
            joinColumns = @JoinColumn(name = "specialitist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id")
    )
    private List<UserInfo> admin;

    // Custom getter method for noOfDoctor
/*    public String getNoOfDoctor() {
        if (doctors != null) {
            return String.valueOf(doctors.size());
        }
        return "0";
    }*/

}
