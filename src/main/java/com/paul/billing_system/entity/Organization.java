package com.paul.billing_system.entity;

import com.paul.billing_system.enums.OrganizationTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Address Should Not Be Empty")
    private String address;

    private String contact;

    @Enumerated(EnumType.STRING)
    private OrganizationTypes type;

    @Email(message = "Email should be valid")
    @Column(unique = true)
    @NotEmpty(message = "Email should not be empty")
    private String email;

    private String emergencyContact;

    private String operatingHour;

/*    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "organization_department",
            joinColumns = @JoinColumn(name = "organization_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private List<Department> departments;*/

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "organization_medspecialists",
            joinColumns = @JoinColumn(name = "organization_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medspecialists_id")
    )
    private List<Specialist> specialists;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "organization_admins",
            joinColumns = @JoinColumn(name = "organization_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id")
    )
    private List<UserInfo> admins;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "organization_patients",
            joinColumns = @JoinColumn(name = "organization_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patients_id")
    )
    private List<Patients> patients;


/*    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "organization_compounders",
            joinColumns = @JoinColumn(name = "organization_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "compounder_id")
    )
    private List<Compounders> compounders;*/

}
