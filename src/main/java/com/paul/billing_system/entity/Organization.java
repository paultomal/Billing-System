package com.paul.billing_system.entity;

import com.paul.billing_system.enums.OrganizationTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class Organization extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotBlank(message = "OrgCode is mandatory!!!")
    @Column(unique = true)
    private String orgCode;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "organization_specialists",
            joinColumns = @JoinColumn(name = "organization_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "specialists_id")
    )
    private List<Specialist> specialists;

}