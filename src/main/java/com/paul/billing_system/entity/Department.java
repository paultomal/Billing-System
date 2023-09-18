/*
package com.paul.billing_system.entity;

import com.paul.billing_system.dto.DepartmentDTO;
import com.paul.billing_system.dto.ServicesInfoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Department name should not be empty")
    private String deptName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "department_services",
            joinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "services_id")
    )
    private List<ServicesInfo> services;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "department_specialists",
            joinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "specialists_id")
    )
    private List<Specialist> specialists;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "department_staffs",
            joinColumns = @JoinColumn(name = "department_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<UserInfo> staffs;
}
*/
