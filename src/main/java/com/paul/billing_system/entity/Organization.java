package com.paul.billing_system.entity;

import com.paul.billing_system.dto.AdminDTO;
import com.paul.billing_system.enums.OrganizationTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    private OrganizationTypes type;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "organization_department",
            joinColumns = @JoinColumn(name = "organization_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "depertment_id")
    )
    private List<Department> departments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "organization_patients",
            joinColumns = @JoinColumn(name = "organization_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patients_id")
    )
    private List<Patients> patients;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "organization_admins",
            joinColumns = @JoinColumn(name = "organization_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id")
    )
    private List<UserInfo> admins;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "organization_compounders",
            joinColumns = @JoinColumn(name = "organization_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "compounder_id")
    )
    private List<Compounders> compounders;
}
