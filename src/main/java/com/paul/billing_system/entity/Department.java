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

    private Long noOfPatients;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "department_services",
            joinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "services_id")
    )
    private List<ServicesInfo> services;


    public static Department form(DepartmentDTO departmentDTO){
        Department department = new Department();
        department.setDeptName(departmentDTO.getDeptName());
        department.setNoOfPatients(departmentDTO.getNoOfPatients());
        department.setServices(departmentDTO.getServices().stream().map(ServicesInfo::form).toList());
        return department;
    }



}
